package com.black.trade.external;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.black.trade.external.**.mapper")
@SpringBootApplication
public class DefaultExternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(DefaultExternalApplication.class, args);
    }
}
