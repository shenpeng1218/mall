package com.moss.result;

public class CodeMessage {

    private int code;

    private String message;

    //所有的异常在这里维护
    public static CodeMessage SUCCESS = new CodeMessage(0, "success");
    public static CodeMessage SERVER_ERROR = new CodeMessage(500100, "服务器错误！");

    //登录模块
    public static CodeMessage PASSWORD_EMPTY = new CodeMessage(500201, "密码不能为空！");
    public static CodeMessage CELLPHONE_NUM_EMPTY = new CodeMessage(500202, "手机号不能为空！");
    public static CodeMessage CELLPHONE_FORMAT_ERROR = new CodeMessage(500203, "手机号格式错误！");
    public static CodeMessage CELLPHONE_NOT_EXITE = new CodeMessage(500204, "手机号不存在！");
    public static CodeMessage PASSWORD_ERROR = new CodeMessage(500205, "密码错误！");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
