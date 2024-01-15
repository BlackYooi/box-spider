package com.aposs.box.spider.service;

import com.aposs.box.spider.domain.stock.pipeline.KlinePipeline;
import com.aposs.box.spider.domain.stock.processor.KlineProcessor;
import com.aposs.box.spider.domain.stock.pipeline.StockInfoPipeline;
import com.aposs.box.spider.domain.stock.processor.StockInfoProcessor;
import com.aposs.box.spider.domain.stock.dao.StockInfoDao;
import com.aposs.box.spider.domain.stock.dao.TradingDateRecordMapper;
import com.aposs.box.spider.domain.stock.entity.StockInfo;
import com.aposs.box.spider.domain.stock.entity.TradingDateRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 股票爬虫service
 *
 * @author Aaron
 * @date 2020/12/19
 */
@Service
public class StockSpiderService {
    private Logger logger = LoggerFactory.getLogger(StockSpiderService.class);
    @Resource
    private StockInfoProcessor stockInfoProcessor;
    @Resource
    private StockInfoPipeline stockInfoPipeline;
    @Resource
    private KlineProcessor klineProcessor;
    @Resource
    private KlinePipeline klinePipeline;
    @Resource
    private StockInfoDao stockInfoDao;
    @Resource
    private TradingDateRecordMapper tradingDateRecordMapper;

    /**
     * 执行股票信息爬取任务
     * 初始化tbl_stock_info数据时使用
     */
    public void runStockInfoSpider() {
        logger.info("------------ start runStockInfoSpider ... --------------");
        String url = "http://96.push2.eastmoney.com/api/qt/clist/get" +
                "?cb=jQuery112406074246312303457_1608308070107" +
                "&pn=1&pz=5000&po=0&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281" +
                "&fltt=2&invt=2&fid=f12&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23" +
                "&fields=f12,f14" +
                "&_=1608308070112";
        Spider.create(stockInfoProcessor).addUrl(url).addPipeline(stockInfoPipeline).run();
        logger.info("----------- runStockInfoSpider finished! ---------------");
    }
    public void runKlineSpider(Integer limit) {
        runKlineSpider(limit, null, null, null, null);
    }

    /**
     * 执行k线数据爬取任务
     *
     * @param limit
     * @param startCode
     * @param endCode
     */
    public void runKlineSpider(Integer limit, String startCode, String endCode, Integer idStart, Integer idEnd) {
        logger.info("------------ start runKlineSpider ... --------------");
        List<StockInfo> stockInfoList = stockInfoDao.getStockInfoRange(startCode, endCode, idStart, idEnd);
        String[] urls = stockInfoList.stream()
                .map(stockInfo -> jointKlineUrl(stockInfo.getCode(), limit))
                .toArray(String[]::new);
        Spider.create(klineProcessor)
                .addPipeline(klinePipeline)
                .addUrl(urls)
                .run();
        logger.info("----------- runKlineSpider finished! ---------------");
    }

    /**
     * 检查传入日期是否为交易日
     * @param date 日期
     * @return true:交易日
     */
    public Boolean checkTradingDate(LocalDate date) {
        TradingDateRecord record = tradingDateRecordMapper.selectByTradingDate(date);
        return record != null;
    }

    /**
     * 拼接股票k线数据url
     *
     * @param code   股票编码
     * @param limit  获取记录数，默认250
     */
    public String jointKlineUrl(String code, Integer limit) {
        // 市场前缀，0 深证、北证，1 上证
        String prefix = code.startsWith("60") ? "1" : "0";
        String secid =  prefix + "." + code;
        long time = new Date().getTime();
        if (limit == null) limit = 250;
        String url = "http://35.push2his.eastmoney.com/api/qt/stock/kline/get" +
                "?cb=jQuery112405483852141023604_1608302055953" +
                "&secid=" + secid +
                "&ut=fa5fd1943c7b386f172d6893dbfba10b" +
                "&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61" +
                "&klt=101&fqt=0" +
                "&end=20500101" +
                "&lmt=" + limit +
                "&_=" + time;
        return url;
    }


}
