package com.aposs.box.spider;

import com.black.trade.access.domain.stock.NewStockSpider;
import com.black.trade.access.domain.stock.StockRealTimeSpider;
import com.black.trade.access.domain.stock.dao.TradingDateRecordMapper;
import com.black.trade.access.service.StockSpiderService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author Aaron
 * @date 2020/12/19
 */
@SpringBootTest
public class StockTest {

    @Resource
    private StockSpiderService stockSpiderService;

    @Resource
    private StockRealTimeSpider stockRealTimeSpider;

    @Resource
    private NewStockSpider newStockSpider;

    @Resource
    private TradingDateRecordMapper tradingDateRecordMapper;



    @Test
    public void test() {
//        stockSpiderService.runStockInfoSpider();
//        stockSpiderService.crawKline("000001",0,1);
//        stockSpiderService.runKlineSpider(250, "300605", null);
//        stockRealTimeSpider.checkAndUpdateTradingDate();
//        newStockSpider.crawNewStockInfo();
//        stockRealTimeSpider.checkAndUpdateTradingDate();

//        stockSpiderService.runKlineSpider(2);
//        stockSpiderService.runKlineSpider(20,"000001","000001");

//        LocalDate localDate = tradingDateRecordMapper.selectMaxDateRecord();
//        System.out.println(localDate);

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.from(dateTimeFormatter.parse("2020-12-19"));
//        TradingDateRecord tradingDateRecord = tradingDateRecordMapper.selectByTradingDate(localDate);
//        System.out.println(tradingDateRecord);

        stockRealTimeSpider.checkAndUpdateTradingDate();

    }


    @Test
    public void runStockInfoSpiderTest(){
        stockSpiderService.runStockInfoSpider();
    }

    @Test
    public void initK() {
        stockSpiderService.runKlineSpider(2, null, null, null, null);
    }

}
