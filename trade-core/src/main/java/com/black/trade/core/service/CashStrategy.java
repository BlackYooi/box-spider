package com.black.trade.core.service;

import com.black.trade.core.bean.StockHoldContext;
import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.core.bean.StockInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface CashStrategy {

    List<StockTimeInfo> focus(List<StockTimeInfo> stockInfoList, LocalDateTime currentTime);

    Boolean applyCash(StockTimeInfo applyFor, StockHoldContext stockHoldContext);

    StockTimeInfo selectApply(List<StockTimeInfo> stockInfoList);

    void doApply(StockTimeInfo applyFor, StockHoldContext stockHoldContext);

    void buyMore(StockTimeInfo sale, StockHoldContext stockHoldContext);

    Boolean saleCash(StockTimeInfo sale, StockHoldContext stockHoldContext);

    void doSale(StockTimeInfo sale, StockHoldContext stockHoldContext);
}
