package com.black.trade.access.controller;

import com.black.trade.access.model.base.BaseResponse;
import com.black.trade.access.service.SimpleSpiderService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ailun
 * @date 2021-07-27
 */
@RestController
@RequestMapping("spider")
public class SpiderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private SimpleSpiderService simpleSpiderService;

    /**
     * 执行spider任务
     * @return
     */
    @GetMapping("/execute")
    public BaseResponse executeSpider(){
        logger.info("执行spider任务...");
        simpleSpiderService.runAllSimpleSpider();
        return BaseResponse.success();
    }

}
