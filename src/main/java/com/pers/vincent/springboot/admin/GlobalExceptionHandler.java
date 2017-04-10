package com.pers.vincent.springboot.admin;

import com.pers.vincent.springboot.demo.domain.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * User: vincent
 * Date: 2017/4/10
 * Comment: 全局统一异常处理，返回的是 HTML。
 *
 * @ControllerAdvice 作用于所有 @Controller 标注的 Controller 类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ERROR 页面路径
     */
    private static final String ERROR_VIEW = "/500";


    /**
     * @param request 请求
     * @param e       异常
     * @return 视图模型
     * @throws Exception 抛出异常
     * @ExceptionHandler: 作用于所有 @RequestMapping 标注的方法抛出的指定类型的异常。
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        Result result = this.getResult(500, e.getMessage(), request.getRequestURL().toString(), e.getStackTrace());
        mv.addObject("result", result);
        mv.setViewName(ERROR_VIEW);
        return mv;
    }

    private Result getResult(int code, String message, String url, StackTraceElement[] elements) {
        String stackTrace = "";
        for (StackTraceElement element : elements) {
            stackTrace += element + "\n";
        }
        return new Result(code, message, url, stackTrace);
    }
}
