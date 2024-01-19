package com.black.trade.access.domain.stock.dao;

import com.black.trade.access.config.MyMapper;
import com.black.trade.access.domain.stock.entity.Kline;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Lazy;

/**
 * @author Aaron
 * @date 2020/12/19
 */
@Lazy
@Mapper
public interface KlineMapper extends MyMapper<Kline> {
}
