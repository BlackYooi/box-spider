package com.black.trade.external.service;

import com.black.trade.external.constant.MarketStatue;

import java.time.LocalDateTime;

public interface MarketInfoService {

    MarketStatue marketStatue(LocalDateTime currentTime);
}
