package com.brl.sc.entity.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class BasePageCondition<T> implements Serializable {

    private Integer page = 1;

    private Integer pageSize = 10;

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

    /**
     * 获取分页对象
     * */
    public Page getPageable(){
        return new Page<T>(this.page, this.pageSize);
    }
}
