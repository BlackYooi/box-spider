package com.black.trade.core.bean;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

@Data
public class TimeConText {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime currentTime;

    private Integer newRoundSecond;

    public Optional<LocalDateTime> getThisRoundAndAdd() {
        Optional<LocalDateTime> thisRound = getThisRound();
        if (thisRound.isPresent()) {
            currentTime = currentTime.minusSeconds(newRoundSecond);
        }
        return thisRound;
    }

    public Optional<LocalDateTime> getThisRound() {
        return currentTime.isBefore(endTime) ? Optional.of(currentTime) : Optional.empty();
    }
}
