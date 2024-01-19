package com.black.trade.access.domain.stock.dao;

import com.black.trade.access.config.MyMapper;
import com.black.trade.access.domain.stock.entity.TradingDateRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * @author Aaron
 * @date 2020/12/20
 */
@Mapper
public interface TradingDateRecordMapper extends MyMapper<TradingDateRecord> {
    LocalDate selectMaxDateRecord();

    TradingDateRecord selectByTradingDate(@Param("tradingDate") LocalDate tradingDate);

}
