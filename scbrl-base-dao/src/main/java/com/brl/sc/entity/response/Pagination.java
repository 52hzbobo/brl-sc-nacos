package com.brl.sc.entity.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chenxueqi
 *
 * 分页参数构造
 */

@Getter
@Setter
@NoArgsConstructor
@ApiModel("分页信息")
public class Pagination implements Serializable {

    private static final long serialVersionUID = -6580423084957921767L;

    @ApiModelProperty("总数")
    private Long total = 0L;

    @ApiModelProperty("总页数")
    private Long totalPages = 0L;

    @ApiModelProperty("每页数量")
    private Long perPage = 10L;

    @ApiModelProperty("当前页码")
    private Long currentPage = 1L;

    @ApiModelProperty("是否有下一页")
    private Boolean hasNext = false;

    @ApiModelProperty("是否有前一页")
    private Boolean hasPrevious = false;

    public static Pagination generate(IPage page) {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(page.getCurrent());
        pagination.setPerPage(page.getSize());
        pagination.setTotal(page.getTotal());
        pagination.setTotalPages(page.getPages());
        pagination.setHasPrevious(page.getCurrent() > 1L);
        pagination.setHasNext(page.getCurrent() < page.getPages());
        return pagination;
    }

}
