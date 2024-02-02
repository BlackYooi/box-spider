package com.black.trade.core.factory;

import com.black.trade.core.bean.StrategyContext;
import com.black.trade.core.bean.TimeConText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeContextFactory {

    public static TimeConText of(StrategyContext strategyContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TimeConText timeConText = new TimeConText();
        timeConText.setStartTime(LocalDateTime.parse(strategyContext.startTime(), formatter));
        timeConText.setCurrentTime(timeConText.getStartTime());
        timeConText.setEndTime(LocalDateTime.parse(strategyContext.endTime(), formatter));
        timeConText.setNewRoundSecond(strategyContext.newRoundSecond());
        return timeConText;
    }
}
