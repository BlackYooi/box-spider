package com.aposs.box.spider.scheduler;

import com.aposs.box.spider.BoxSpiderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunnerTest {

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
