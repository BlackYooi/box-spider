package com.black.trade.core.service;

import com.black.trade.core.bean.*;
import com.black.trade.core.util.DataUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CashManager {

    private StockHoldContext stockHoldContext;

    private TimeConText timeConText;

    private CashStrategy cashStrategy;

    private ReportService reportService;

    private RealtimeStockDataRepository realtimeStockDataRepository;

    private StockHistoryDataRepository stockHistoryDataRepository;

    public void run() {
        while (timeConText.getThisRound().isPresent()) {
            LocalDateTime currentTime = timeConText.getThisRoundAndAdd().get();
            // 检测
            detecting(currentTime);
            // 同步新数据到历史数据库
            syncToHistory(currentTime);
            // 购买
            tryBuy(currentTime);
        }
        // 上报结果
        reportService.report(timeConText, stockHoldContext);
    }

    private void detecting(LocalDateTime currentTime) {
        // 刷新关注列表的数据
        flushFocus(currentTime);
        // 出售
        sale();
        // 购买更多
        tryBuyMore();
    }

    private void syncToHistory(LocalDateTime currentTime) {
        realtimeStockDataRepository.syncToHistory(currentTime);
    }

    private void tryBuy(LocalDateTime currentTime) {
        // 拿到所有历史数据
        List<StockTimeInfo> historyData = stockHistoryDataRepository.historyData();
        // 通过策略得到关注的数据
        List<StockTimeInfo> focus = cashStrategy
                .focus(historyData, currentTime)
                .stream()
                .filter(info -> cashStrategy.applyCash(info, stockHoldContext))
                .collect(Collectors.toList());
        buy(focus);
    }

    private void flushFocus(LocalDateTime currentTime) {
        List<StockTimeInfo> currentInfo = realtimeStockDataRepository.getCurrentInfo(currentTime);
        List<StockTimeInfo> times = stockHoldContext.getStillHold()
                .stream()
                .map(StockHoldContext.HoldStockInfo::getStockTimeInfo)
                .collect(Collectors.toList());
        DataUtil.matchNewDataAndThen(times, currentInfo, timeInfo -> timeInfo.getStockInfo().getCode(), (o, n) -> {
            o.getPrices().addAll(n.getPrices());
        });
    }

    private void sale() {
        Collection<StockHoldContext.HoldStockInfo> infos = stockHoldContext.getStillHold();
        for (StockHoldContext.HoldStockInfo info : infos) {
            Boolean needSale = cashStrategy.saleCash(info.getStockTimeInfo(), stockHoldContext);
            if (needSale) {
                cashStrategy.doSale(info.getStockTimeInfo(), stockHoldContext);
            }
        }
    }

    private void tryBuyMore() {
        stockHoldContext.getStillHold().forEach(info -> {
            cashStrategy.buyMore(info.getStockTimeInfo(), stockHoldContext);
        });
    }

    private void buy(List<StockTimeInfo> focus) {
        if (focus == null || focus.isEmpty()) {
            return;
        }
        // 从关注的对象中选出一个
        StockTimeInfo stockInfo = cashStrategy.selectApply(focus);
        cashStrategy.doApply(stockInfo, stockHoldContext);
        // 更新关注列表
        List<StockTimeInfo> newFocus = focus.stream()
                .filter(i -> !Objects.equals(i, stockInfo))
                .filter(i -> cashStrategy.applyCash(i, stockHoldContext))
                .collect(Collectors.toList());
        // 继续递归
        buy(newFocus);
    }


}
