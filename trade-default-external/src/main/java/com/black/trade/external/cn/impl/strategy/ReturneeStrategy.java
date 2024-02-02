package com.black.trade.external.cn.impl.strategy;

import com.black.trade.core.bean.StockHoldContext;
import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.core.service.CashStrategy;
import com.black.trade.external.cn.bean.CnStrategyContext;
import com.black.trade.external.cn.bean.StrategyParam;

import java.time.LocalDateTime;
import java.util.List;

public class ReturneeStrategy implements CashStrategy<StrategyParam, CnStrategyContext> {

    @Override
    public List<StockTimeInfo> focus(CnStrategyContext strategyContext, List<StockTimeInfo> stockInfoList, LocalDateTime currentTime) {
        return null;
    }

    @Override
    public Boolean applyCash(CnStrategyContext strategyContext, StockTimeInfo applyFor, StockHoldContext stockHoldContext) {
        return null;
    }

    @Override
    public StockTimeInfo selectApply(CnStrategyContext strategyContext, List<StockTimeInfo> stockInfoList) {
        return null;
    }

    @Override
    public void doApply(CnStrategyContext strategyContext, StockTimeInfo applyFor, StockHoldContext stockHoldContext) {

    }

    @Override
    public void buyMore(CnStrategyContext strategyContext, StockTimeInfo sale, StockHoldContext stockHoldContext) {

    }

    @Override
    public Boolean saleCash(CnStrategyContext strategyContext, StockTimeInfo sale, StockHoldContext stockHoldContext) {
        return null;
    }

    @Override
    public void doSale(CnStrategyContext strategyContext, StockTimeInfo sale, StockHoldContext stockHoldContext) {

    }
}
