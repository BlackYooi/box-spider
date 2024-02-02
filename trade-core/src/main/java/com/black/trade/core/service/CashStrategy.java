package com.black.trade.core.service;

import com.black.trade.core.bean.StockHoldContext;
import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.core.bean.StockInfo;
import com.black.trade.core.bean.StrategyContext;

import java.time.LocalDateTime;
import java.util.List;

public interface CashStrategy<PARAM, ST_CTX extends StrategyContext<PARAM>> {

    List<StockTimeInfo> focus(ST_CTX strategyContext, List<StockTimeInfo> stockInfoList, LocalDateTime currentTime);

    Boolean applyCash(ST_CTX strategyContext, StockTimeInfo applyFor, StockHoldContext stockHoldContext);

    StockTimeInfo selectApply(ST_CTX strategyContext, List<StockTimeInfo> stockInfoList);

    void doApply(ST_CTX strategyContext, StockTimeInfo applyFor, StockHoldContext stockHoldContext);

    void buyMore(ST_CTX strategyContext, StockTimeInfo sale, StockHoldContext stockHoldContext);

    Boolean saleCash(ST_CTX strategyContext, StockTimeInfo sale, StockHoldContext stockHoldContext);

    void doSale(ST_CTX strategyContext, StockTimeInfo sale, StockHoldContext stockHoldContext);
}
