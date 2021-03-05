package com.qsn.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 订单模启动类
 *
 * @author qiusn
 * @date 2020-01-08
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.qsn.order.*.mapper")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
