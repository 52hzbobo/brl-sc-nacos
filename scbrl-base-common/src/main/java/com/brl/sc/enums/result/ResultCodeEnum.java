package com.brl.sc.enums.result;

import com.brl.sc.enums.base.BaseResultCode;

/**
 * 返回码-枚举<br/>
 * 异常：<br/>
 * 1xxxx:AUTH 登录认证<br/>
 * 2xxxx:USER 用户<br/>
 * 3xxxx:CHAT_MASSAGE 聊天消息<br/>
 * 4xxxx:CHAT_FILE 文件<br/>
 * 5xxxx:CHAT_GROUP 群组<br/>
 * 6xxxx:CHAT_ROOM 房间
 *
 */
public enum ResultCodeEnum implements BaseResultCode {
    ERROR(-1,"系统异常,请联系管理员!"),
    SUCCEED(1,"Succeed"),
    BUSINESS(0,"（统一业务提示编码）"),
    PARAM_ERROR(2,"参数错误"),
    ;

    private int code ;

    private String msg ;

    ResultCodeEnum(int code, String msg) {
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
