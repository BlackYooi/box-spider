package com.aposs.box.spider.scheduler;

import com.black.trade.access.BoxSpiderRunner;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.black.trade.access.BoxSpiderApplication;


@SpringBootTest(classes = {BoxSpiderApplication.class})
public final class RunnerTest {

    @Resource
    private BoxSpiderRunner boxSpiderRunner;

    /**
     * 新闻
     */
    @Test
    public void processNewsSpiderScheduleTest() {
        boxSpiderRunner.processNewsSpiderSchedule();
    }

    /**
     * 日期列表
     */
    @Test
    public void processCheckTradingDateScheduleTest() {
        boxSpiderRunner.processCheckTradingDateSchedule();
    }

    /**
     * 数据
     */
    @Test
    public void processStockSpiderScheduleTest() {
        boxSpiderRunner.processStockSpiderSchedule();
    }
}
