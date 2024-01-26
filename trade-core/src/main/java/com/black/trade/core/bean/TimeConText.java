package com.black.trade.core.bean;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

public class TimeConText {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime currentTime;

    private Function<LocalDateTime, LocalDateTime> nextRound;

    public Optional<LocalDateTime> getThisRoundAndAdd() {
        Optional<LocalDateTime> thisRound = getThisRound();
        if (thisRound.isPresent()) {
            currentTime = nextRound.apply(currentTime);
        }
        return thisRound;
    }

    public Optional<LocalDateTime> getThisRound() {
        return currentTime.isBefore(endTime) ? Optional.of(currentTime) : Optional.empty();
    }
}
