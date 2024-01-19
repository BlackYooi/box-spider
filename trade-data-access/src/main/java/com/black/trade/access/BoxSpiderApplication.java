package com.black.trade.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Aaron
 * @date 2020/11/25
 * <p>
 * 程序启动入口
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan(value = {"com.black.**.mapper", "com.black.**.dao"})
public class BoxSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoxSpiderApplication.class, args);
    }
}
