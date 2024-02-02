package com.black.trade.external.cn.impl;

import com.black.trade.external.constant.MarketStatue;
import com.black.trade.external.service.MarketInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class CnMarketInfoServiceImpl implements MarketInfoService {

    @Value(value = "${td.cn.stock.open.time}")
    private String cnOpenTime;

    @Value(value = "${td.cn.stock.close.time}")
    private String cnCloseTime;

    @Override
    public MarketStatue marketStatue(LocalDateTime currentTime) {
        // TODO wenquan 区分节假日
        LocalTime openTime = LocalTime.parse(cnOpenTime);
        LocalTime closeTime = LocalTime.parse(cnCloseTime);
        LocalTime time = currentTime.toLocalTime();
        return (openTime.isAfter(time) || closeTime.isBefore(time)) ? MarketStatue.CLOSE : MarketStatue.OPEN;
    }
}
