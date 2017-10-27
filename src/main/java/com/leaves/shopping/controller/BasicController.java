package com.leaves.shopping.controller;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 拦截错误路径的报错，返回相应错误页面
 * @author 偶是小菜鸟
 * Create Time: 2017-10-26 11:09
 */
@ControllerAdvice
public class BasicController {

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public String requestMissingServletRequest() {
        return "error/400";
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public String methodNotAllowed() {
        return "error/405";
    }
}
