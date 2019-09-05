package com.vincent.example.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IDEA.
 * User: e
 * Date: 2017/7/11
 * Comment: 全局异常配置（返回视图）
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ErrorMessage<String> jsonErrorHandler(HttpServletRequest request, BaseException exception) {
        ErrorMessage<String> errorMessage = new ErrorMessage<>();
        errorMessage.setCode(500);
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setUrl(request.getRequestURL().toString());
        return errorMessage;
    }
}
