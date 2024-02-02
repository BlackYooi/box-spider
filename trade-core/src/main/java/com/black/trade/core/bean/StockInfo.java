package com.black.trade.core.bean;

import lombok.Data;

@Data
public class StockInfo {

    private String code;

    public StockInfo(String code) {
        this.code = code;
    }
}
