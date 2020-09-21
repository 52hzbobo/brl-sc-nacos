package com.brl.sc.controller;

import com.brl.sc.dao.StockBaseMapper;
import com.brl.sc.entity.po.StockBasePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.concurrent.TimeUnit;
@Slf4j
@ApiIgnore
@RestController
@RequestMapping(value = "/test")
public class TestAdminController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private StockBaseMapper stockBaseMapper;


    @GetMapping("/get")
    private String get(@RequestParam(value = "value",required = false) String value){
        log.info("测试Admin打印~~");
        StockBasePO po = stockBaseMapper.selectById(3144058L);
            redisTemplate.opsForValue().set("AccountPO:test1","test.toString()",24, TimeUnit.HOURS);
            //throw new BusinessException("测试业务异常");//测试-业务异常
        //Long.valueOf("1F");// 测试-系统异常
        return "测试-请求Value:"+value+"|Admin-后台服务返回时间:"+System.currentTimeMillis();
    }



}
