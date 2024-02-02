package com.black.trade.core.service;

import com.black.trade.core.bean.*;
import com.black.trade.core.factory.TimeContextFactory;
import com.black.trade.core.util.DataUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 回测器
 * @param <ST_CTX> 计算参数
 */
public class Backtester<PARAM, ST_CTX extends StrategyContext<PARAM>> {

    private StockHoldContext stockHoldContext;
    private CashStrategy<PARAM,ST_CTX> cashStrategy;

    private ReportService reportService;

    private RealtimeStockDataRepository realtimeStockDataRepository;

    private StockHistoryDataRepository<PARAM,ST_CTX> stockHistoryDataRepository;

    public void run(ST_CTX strategyContext) {
        TimeConText timeConText = TimeContextFactory.of(strategyContext);
        while (timeConText.getThisRound().isPresent()) {
            LocalDateTime currentTime = timeConText.getThisRoundAndAdd().get();
            // 检测
            detecting(strategyContext, currentTime);
            // 同步新数据到历史数据库
            syncToHistory(currentTime);
            // 购买
            tryBuy(strategyContext, currentTime);
        }
        // 上报结果
        reportService.report(strategyContext, timeConText, stockHoldContext);
    }

    private void detecting(ST_CTX strategyContext, LocalDateTime currentTime) {
        // 刷新关注列表的数据
        flushFocus(strategyContext, currentTime);
        // 出售
        sale(strategyContext);
        // 购买更多
        tryBuyMore(strategyContext);
    }

    private void syncToHistory(LocalDateTime currentTime) {
        realtimeStockDataRepository.syncToHistory(currentTime);
    }

    private void tryBuy(ST_CTX strategyContext, LocalDateTime currentTime) {
        // 拿到所有历史数据
        List<StockTimeInfo> historyData = stockHistoryDataRepository.historyData(strategyContext, currentTime);
        // 通过策略得到关注的数据
        List<StockTimeInfo> focus = cashStrategy
                .focus(strategyContext, historyData, currentTime)
                .stream()
                .filter(info -> cashStrategy.applyCash(strategyContext, info, stockHoldContext))
                .collect(Collectors.toList());
        buy(strategyContext, focus);
    }

    private void flushFocus(ST_CTX strategyContext, LocalDateTime currentTime) {
        List<StockTimeInfo> currentInfo = realtimeStockDataRepository.getCurrentInfo(currentTime);
        List<StockTimeInfo> times = stockHoldContext.getStillHold()
                .stream()
                .map(StockHoldContext.HoldStockInfo::getStockTimeInfo)
                .collect(Collectors.toList());
        DataUtil.matchNewDataAndThen(times, currentInfo, timeInfo -> timeInfo.getStockInfo().getCode(), (o, n) -> {
            o.getPrices().addAll(n.getPrices());
        });
    }

    private void sale(ST_CTX strategyContext) {
        Collection<StockHoldContext.HoldStockInfo> infos = stockHoldContext.getStillHold();
        for (StockHoldContext.HoldStockInfo info : infos) {
            Boolean needSale = cashStrategy.saleCash(strategyContext, info.getStockTimeInfo(), stockHoldContext);
            if (needSale) {
                cashStrategy.doSale(strategyContext, info.getStockTimeInfo(), stockHoldContext);
            }
        }
    }

    private void tryBuyMore(ST_CTX strategyContext) {
        stockHoldContext.getStillHold().forEach(info -> {
            cashStrategy.buyMore(strategyContext, info.getStockTimeInfo(), stockHoldContext);
        });
    }

    private void buy(ST_CTX strategyContext, List<StockTimeInfo> focus) {
        if (focus == null || focus.isEmpty()) {
            return;
        }
        // 从关注的对象中选出一个
        StockTimeInfo stockInfo = cashStrategy.selectApply(strategyContext, focus);
        cashStrategy.doApply(strategyContext, stockInfo, stockHoldContext);
        // 更新关注列表
        List<StockTimeInfo> newFocus = focus.stream()
                .filter(i -> !Objects.equals(i, stockInfo))
                .filter(i -> cashStrategy.applyCash(strategyContext, i, stockHoldContext))
                .collect(Collectors.toList());
        // 继续递归
        buy(strategyContext, newFocus);
    }


}
