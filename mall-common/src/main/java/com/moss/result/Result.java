package com.moss.result;

public class Result<T> {

    private int code;

    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 私有化构造方法
     * @param data
     */
    private Result(T data) {
        this.code = 0;
        this.message = "success";
        this.data = data;
    }

    private Result(CodeMessage codeMessage){
        if(codeMessage == null){
            return;
        }
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }

    /**
     * 成功时调用
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 失败时调用
     */
    public static <T> Result<T> error(CodeMessage codeMessage){
        return new Result<T>(codeMessage);
    }
}
