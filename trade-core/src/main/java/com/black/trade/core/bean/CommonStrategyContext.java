package com.black.trade.core.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonStrategyContext implements StrategyContext<Integer> {

    private String startTime;

    private String endTime;

    private Integer newRoundSecond;

    @Override
    public Integer param() {
        throw new RuntimeException("who call me");
    }

    @Override
    public String startTime() {
        return startTime;
    }

    @Override
    public String endTime() {
        return endTime;
    }

    @Override
    public Integer newRoundSecond() {
        return newRoundSecond;
    }
}
