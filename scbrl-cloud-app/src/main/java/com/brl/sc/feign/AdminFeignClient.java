package com.brl.sc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("scbrl-cloud-admin")
public interface AdminFeignClient {

    @GetMapping("/test/get")
    String get(@RequestParam(value = "value",required = false) String value);


}
