package com.black.trade.external.cn.test.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.black.trade.external.cn.DO.KDO;
import com.black.trade.external.cn.mapper.StockCnKLineMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class KDOMapperTest {

    @Resource
    private StockCnKLineMapper stockCnKLineMapper;

    @Test
    public void searchTest() {
        List<KDO> sCode = stockCnKLineMapper.selectList(new QueryWrapper<>(KDO.class).eq("s_code", "000001"));
        Assert.notNull(sCode, "sCode不能为null");
        Assert.noNullElements(sCode, "sCode存在为null的元素");
    }
}
