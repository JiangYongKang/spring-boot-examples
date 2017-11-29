package com.person.vincent;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IDEA.
 * User: e
 * Date: 2017/7/11
 * Comment: 全局异常配置（返回视图）
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    // 发生异常返回试图
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }

    // 发生异常返回 JSON 格式数据
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request, GlobalException e) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setUrl(request.getRequestURL().toString());
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setMessage(e.getMessage());
        errorInfo.setData("Some Data");
        return errorInfo;
    }
}
