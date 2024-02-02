package com.black.trade.external.cn.test.convertor;

import com.black.trade.core.bean.Price;
import com.black.trade.external.cn.DO.KDO;
import com.black.trade.external.cn.convertor.PriceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PriceConvertorTest {

    @Test
    public void test() {
        KDO kdo = new KDO();
        kdo.setS_close(BigDecimal.valueOf(12));
        kdo.setS_date(LocalDate.now());
        kdo.setS_open(BigDecimal.valueOf(13));
        Price price = PriceMapper.INSTANCE.kDOToPrice(kdo);
        Assert.notNull(price.getCurrentPrice(), "currentPrice不能为空");
        Assert.notNull(price.getTime(), "time不能为空");
        Assert.notNull(price.getOpen(), "open不能为空");
    }
}
