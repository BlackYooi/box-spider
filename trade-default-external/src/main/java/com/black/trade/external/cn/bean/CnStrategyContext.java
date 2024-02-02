package com.black.trade.external.cn.bean;

import com.black.trade.core.bean.CommonStrategyContext;
import com.black.trade.core.bean.StrategyContext;

public class CnStrategyContext implements StrategyContext<StrategyParam> {

    private StrategyParam strategyParam;

    private CommonStrategyContext commonStrategyContext;

    public CnStrategyContext(StrategyParam strategyParam, CommonStrategyContext commonStrategyContext) {
        this.strategyParam = strategyParam;
        this.commonStrategyContext = commonStrategyContext;
    }

    @Override
    public StrategyParam param() {
        return strategyParam;
    }

    @Override
    public String startTime() {
        return commonStrategyContext.getStartTime();
    }

    @Override
    public String endTime() {
        return commonStrategyContext.getEndTime();
    }

    @Override
    public Integer newRoundSecond() {
        return commonStrategyContext.getNewRoundSecond();
    }
}
