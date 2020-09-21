package com.brl.sc.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("brl_stock_base")
public class StockBasePO {

    @TableId(type = IdType.AUTO)
    private Long id ;

    private String stockType ;

    private String stockExchange;

    private String stockCode ;

    private String stockName ;
}
