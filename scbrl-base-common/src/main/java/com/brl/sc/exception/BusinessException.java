package com.brl.sc.exception;

import com.brl.sc.enums.base.BaseResultCode;
import com.brl.sc.enums.result.ResultCodeEnum;

public class BusinessException extends RuntimeException {

    private int code ;

    private String msg ;

    private Object data ;

    /**
     * 统一业务异常提示
     * @param msg
     */
    public BusinessException(String msg){
        super(msg);
        this.code = ResultCodeEnum.BUSINESS.getCode();
        this.msg = msg;
    }
    public BusinessException(int code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg,Object data){
        super(msg);
        this.code = ResultCodeEnum.BUSINESS.getCode();
        this.msg = msg;
        this.data = data;
    }

    /**
     * 特殊业务异常提示（特殊编码，移动端根据编码做逻辑场景）
     * @param baseResultCode
     */
    public BusinessException(BaseResultCode baseResultCode){
        super(baseResultCode.getMsg());
        this.code = baseResultCode.getCode();
        this.msg = baseResultCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
