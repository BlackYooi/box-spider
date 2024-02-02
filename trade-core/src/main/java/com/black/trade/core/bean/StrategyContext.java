package com.black.trade.core.bean;

/**
 * 策略上下文，包含策略不可变信息
 */
public interface StrategyContext<PARAM> {

    /**
     * 不可变参数
     * @return
     */
    PARAM param();

    /**
     * 测试起始时间
     * @return
     */
    String startTime();

    /**
     * 测试结束时间
     * @return
     */
    String endTime();

    /**
     * 下一轮间隔时间（单位秒）
     * @return
     */
    Integer newRoundSecond();

}
