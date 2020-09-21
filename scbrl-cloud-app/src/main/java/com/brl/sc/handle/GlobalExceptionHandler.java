package com.brl.sc.handle;


import com.brl.sc.enums.result.ResultCodeEnum;
import com.brl.sc.exception.BusinessException;
import com.brl.sc.entity.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一业务异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException e){
        return BaseResponse.error(e.getCode() , e.getMessage());
    }

    /**
     * 统一业务异常(VO验证)
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidException(MethodArgumentNotValidException e){
        // 默认返回第一个验证失败信息
        return BaseResponse.error(ResultCodeEnum.BUSINESS.getCode(),
                  e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     * 统一系统异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e){
        e.printStackTrace();
        return BaseResponse.error(ResultCodeEnum.ERROR);
    }

}
