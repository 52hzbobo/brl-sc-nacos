package com.brl.sc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoConfiguration
public class BrlSCGatewayApplication {

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        SpringApplication.run(BrlSCGatewayApplication.class, args);
        log.info(">>>>>>>>  scbrl-cloud-gateway 启动成功！耗时：{} ms",(System.currentTimeMillis() - s));
    }

}
