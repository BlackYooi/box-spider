package com.black.trade.external.cn.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@TableName("stock_k_day")
public class KDO {

    @TableField("s_code")
    private String s_code;

    @TableField("s_date")
    private LocalDate s_date;

    @TableField("s_open")
    private BigDecimal s_open;

    @TableField("s_close")
    private BigDecimal s_close;

    @TableField("s_high")
    private BigDecimal s_high;

    @TableField("s_low")
    private BigDecimal s_low;

    @TableField("s_cjl")
    private BigDecimal s_cjl;

    @TableField("s_cje")
    private BigDecimal s_cje;

    @TableField("s_zf")
    private BigDecimal s_zf;

    @TableField("s_zdf")
    private BigDecimal s_zdf;

    @TableField("s_zde")
    private BigDecimal s_zde;

    @TableField("s_hsl")
    private BigDecimal s_hsl;
}
