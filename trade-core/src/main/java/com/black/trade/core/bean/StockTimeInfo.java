package com.black.trade.core.bean;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockTimeInfo {

    @Getter
    private StockInfo stockInfo;

    @Getter
    private List<Price> prices;

    public List<Price> prices(LocalDateTime after, LocalDateTime before) {
        return new ArrayList<>();
    }
}
