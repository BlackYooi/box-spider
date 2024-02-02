package com.black.trade.core.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Price {

    private LocalDateTime time;

    /**
     * 当前价格
     */
    private BigDecimal currentPrice;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal cjl;

    private BigDecimal cje;

    private BigDecimal zf;

    private BigDecimal zdf;

    private BigDecimal zde;

    private BigDecimal hsl;
}
