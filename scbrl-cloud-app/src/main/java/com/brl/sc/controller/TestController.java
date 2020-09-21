package com.brl.sc.controller;

import com.brl.sc.dao.StockBaseMapper;
import com.brl.sc.feign.AdminFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@ApiIgnore
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private StockBaseMapper stockBaseMapper;

    @Autowired
    private AdminFeignClient adminFeignClient;


    @ApiIgnore
    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get(
            @RequestParam(value = "n",required = false) Integer n
    ) {
        log.info("测试App打印~~");
        redisTemplate.opsForValue().set("111","2222222");
        stockBaseMapper.selectById(820958L);
        return adminFeignClient.get("test request feign");
    }

}
