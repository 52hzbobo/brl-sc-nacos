package com.brl.sc.enums.result;

import com.brl.sc.enums.base.BaseResultCode;

/**
 * 后台管理-枚举
 */
public enum AdminResultEnum implements BaseResultCode {
    //ACCOUNT_IS_REGISTER(10001,"后台管理-手机号已注册"),

    INFO_TRANSFORM(10003,"信息转换错误!"),
    OP_FAIL(10004,"操作失败!"),
    MS_UNUSUAL(10005,"状态或模式异常!"),
    ;


    private int code ;

    private String msg ;

    AdminResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
