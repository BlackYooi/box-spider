package com.black.trade.core.service;

import com.black.trade.core.bean.StockTimeInfo;

import java.util.List;

public interface StockHistoryDataRepository {

    List<StockTimeInfo> historyData();
}
