package com.black.trade.core.service;

import com.black.trade.core.bean.StockTimeInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface RealtimeStockDataRepository {

    /**
     * 获得最新的数据
     * @param time
     * @return
     */
    List<StockTimeInfo> getCurrentInfo(LocalDateTime time);

    /**
     * 同步最新数据到历史数据库
     * @param currentTime
     */
    void syncToHistory(LocalDateTime currentTime);
}
