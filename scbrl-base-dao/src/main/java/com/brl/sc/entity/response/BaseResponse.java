package com.brl.sc.entity.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.brl.sc.enums.base.BaseResultCode;
import com.brl.sc.enums.result.ResultCodeEnum;
import com.brl.sc.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chenxueqi
 *
 * 通用的Response
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("返回内容")
public class BaseResponse<T> implements Serializable {

    public static final int SUCCESS = ResultCodeEnum.SUCCEED.getCode();

    @ApiModelProperty("返回码：1-成功 其他-异常")
    private Integer code;

    @ApiModelProperty("异常状态标识")
    private boolean ok;

    @ApiModelProperty("提示内容")
    private String msg;

    @ApiModelProperty("数据")
    private T data;


//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Map<String, Object> meta = new HashMap<>(); //分页参数
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("分页信息(非必须)")
    private Pagination meta; //分页参数

    public BaseResponse(T data) {
        this.data = data;
        this.code = SUCCESS;
        this.ok = true;
        this.msg = "Success";
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.ok = false;
        this.msg = msg;
    }

    public BaseResponse(Integer code, String msg,T data) {
        this.code = code;
        this.ok = false;
        this.msg = msg;
        this.data = data;
    }

    //成功返回
    //Response-不分页
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(null);
    }

    public static <T> BaseResponse<T> success(T t) {
        return new BaseResponse<T>(t);
    }

    //Response-分页
    public static BaseResponse page(Page page) {
        BaseResponse response = BaseResponse.success(page.getRecords());
        response.addPaginationMeta(Pagination.generate(page));
        return response;
    }

    //Response-分页
    public static BaseResponse page(IPage page) {
        BaseResponse response = BaseResponse.success(page.getRecords());
        response.addPaginationMeta(Pagination.generate(page));
        return response;
    }

    //错误返回
    public static <T> BaseResponse<T> error(Integer code, String message) {
        return new BaseResponse<T>(code, message);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<T>(ResultCodeEnum.BUSINESS.getCode(), message);
    }

    public static <T> BaseResponse<T> error(BaseResultCode codeResult) {
        return new BaseResponse<T>(codeResult.getCode(), codeResult.getMsg());
    }

    public static <T> BaseResponse<T> error(String message,T data) {
        return new BaseResponse<T>(ResultCodeEnum.BUSINESS.getCode(), message,data);
    }

    public static <T> BaseResponse<T> error(Integer code, String message,T data) {
        return new BaseResponse<T>(code, message,data);
    }

    //添加分页信息
//    private void addMeta(String name, Object object) {
//        this.meta.remove(name);
//        this.meta.put(name, object);
//    }

    //添加分页信息
    private void addPaginationMeta(Pagination pagination) {
//        this.addMeta("pagination", pagination);
        this.meta = pagination;
    }

    //异常
    public BaseResponse<T> tryThrowException() {
        if (hasError()) {
            throw new BusinessException(code,msg);
        }
        return this;
    }

    public boolean hasError() {
        return code != SUCCESS;
    }

    public boolean isOk() {
        return !hasError();
    }
}

