package com.brl.sc.enums.result;

import com.brl.sc.enums.base.BaseResultCode;

/**
 * 微信小程序-枚举
 */
public enum AppResultEnum implements BaseResultCode {
     INFO_TRANSFORM(13,"信息转换错误!"),
     OP_FAIL(14,"操作失败!"),
     MS_UNUSUAL(15,"状态或模式异常!"),
     CONNECT_ERROR(16,"网络错误!"),
     FREQUENT_REQ(17,"请求过于频繁,请稍后再试!"),


     ;

    private int code ;

    private String msg ;

    AppResultEnum(int code, String msg) {
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
