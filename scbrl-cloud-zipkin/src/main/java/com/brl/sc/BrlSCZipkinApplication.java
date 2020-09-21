package com.brl.sc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import zipkin2.server.internal.EnableZipkinServer;

@Slf4j
@EnableZipkinServer
@SpringBootApplication
public class BrlSCZipkinApplication {

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        SpringApplication.run(BrlSCZipkinApplication.class, args);
        log.info(">>>>>>>>  scbrl-cloud-zipkin 启动成功！耗时：{} ms",(System.currentTimeMillis() - s));
    }

}
