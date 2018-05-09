package com.moss.result;

public class CodeMessage {

    private int code;

    private String message;

    //所有的异常在这里维护
    public static CodeMessage SUCCESS = new CodeMessage(0, "success");
    public static CodeMessage SERVER_ERROR = new CodeMessage(500100, "服务器错误！");

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
