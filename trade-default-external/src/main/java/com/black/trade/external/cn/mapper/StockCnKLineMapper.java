package com.black.trade.external.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.black.trade.external.cn.DO.KDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockCnKLineMapper extends BaseMapper<KDO> {

    List<String> tradeDay(@Param("currentTime") String currentTime, @Param("limit") Integer limit);

}
