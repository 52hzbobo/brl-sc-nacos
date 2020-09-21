package com.brl.sc;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableTransactionManagement
public class BrlSCAppApplication {

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        SpringApplication.run(BrlSCAppApplication.class, args);
        log.info(">>>>>>>> scbrl-cloud-app 启动成功！耗时：{} ms", (System.currentTimeMillis() - s));
    }

}
