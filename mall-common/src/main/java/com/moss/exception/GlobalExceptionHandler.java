package com.moss.exception;

import com.moss.result.CodeMessage;
import com.moss.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();//这里只简单打印，应该用log4j等日志框架

        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;

            return Result.error(ex.getCodeMessage());
        }else if(e instanceof BindException){
            BindException ex = (BindException)e;
            ObjectError error = ex.getAllErrors().get(0);//这里只取一个异常 可以取任意个
            String msg = error.getDefaultMessage();

            return Result.error(CodeMessage.BIND_ERROR.fillArgs(msg));
        }else{
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }
}
