package com.black.trade.external.cn.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.core.service.StockHistoryDataRepository;
import com.black.trade.external.cn.DO.KDO;
import com.black.trade.external.cn.bean.CnStrategyContext;
import com.black.trade.external.cn.bean.StrategyParam;
import com.black.trade.external.cn.convertor.StockConvertor;
import com.black.trade.external.cn.mapper.StockCnKLineMapper;
import com.black.trade.external.constant.MarketStatue;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BlStockCnHistoryDataRepImpl implements StockHistoryDataRepository<StrategyParam, CnStrategyContext> {

    private CnMarketInfoServiceImpl cnMarketInfoService;

    @Resource
    private StockCnKLineMapper stockCnKLineMapper;

    public BlStockCnHistoryDataRepImpl(CnMarketInfoServiceImpl cnMarketInfoService) {
        this.cnMarketInfoService = cnMarketInfoService;
    }

    @Override
    public List<StockTimeInfo> historyData(CnStrategyContext cnStrategyContext, LocalDateTime currentTime) {
        MarketStatue marketStatue = cnMarketInfoService.marketStatue(currentTime);
        LocalDate date = MarketStatue.OPEN.equals(marketStatue)
                ? currentTime.minusDays(1).toLocalDate()
                : currentTime.toLocalDate();
        // 获取jy日
        List<String> list = stockCnKLineMapper.tradeDay(date.format(DateTimeFormatter.ISO_LOCAL_DATE), cnStrategyContext.param().getHistoryDate());
        if (list.isEmpty()) {
            throw new RuntimeException(String.format("day: %s 没有历史数据", date));
        }
        // 获取历史数据
        List<KDO> kdoList = stockCnKLineMapper.selectList(new LambdaQueryWrapper<KDO>().in(KDO::getS_date, list));
        // 封装成vo
        List<StockTimeInfo> stockTimeInfos = StockConvertor.toTimeInfo(kdoList);
        return stockTimeInfos;
    }
}
