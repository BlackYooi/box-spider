package com.black.trade.external.cn.test.repo;

import com.black.trade.core.bean.CommonStrategyContext;
import com.black.trade.core.bean.StockTimeInfo;
import com.black.trade.external.cn.bean.CnStrategyContext;
import com.black.trade.external.cn.bean.StrategyParam;
import com.black.trade.external.cn.impl.BlStockCnHistoryDataRepImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BlStockCnHistoryDataRepImplTest {

    @Autowired
    private BlStockCnHistoryDataRepImpl blStockCnHistoryDataRep;

    @Test
    public void historyDataTest() {
        StrategyParam param = new StrategyParam();
        param.setHistoryDate(10);
        CnStrategyContext context = new CnStrategyContext(param, new CommonStrategyContext());
        List<StockTimeInfo> stockTimeInfos = blStockCnHistoryDataRep.historyData(context, LocalDateTime.now());
        Assert.notEmpty(stockTimeInfos, "stockTimeInfos不能为空");
        Assert.noNullElements(stockTimeInfos, "stockTimeInfos不能有null的元素");

    }
}
