package com.black.trade.core.service;

import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.core.bean.StrategyContext;

import java.time.LocalDateTime;
import java.util.List;

public interface StockHistoryDataRepository<PARAM, ST_CTX extends StrategyContext<PARAM>> {

    List<StockTimeInfo> historyData(ST_CTX ctx, LocalDateTime currentTime);
}
