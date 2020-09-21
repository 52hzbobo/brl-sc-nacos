package com.brl.sc.entity.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseCondition<T> implements Serializable {

    @JsonIgnore
    private QueryWrapper<T> queryWrapper;

    /**
     * 查询条件获取方法
     *
     * @return 查询条件
     */
    public QueryWrapper<T> generate() {
        this.queryWrapper = new QueryWrapper<>();
        buildCustomPredicates(queryWrapper);
        return queryWrapper;
    }

    /**
     * 重写该方法，构造查询条件
     */
    protected abstract void buildCustomPredicates(QueryWrapper<T> queryWrapper);
}
