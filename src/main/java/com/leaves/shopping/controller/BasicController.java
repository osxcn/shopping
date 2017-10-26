package com.leaves.shopping.controller;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 拦截错误路径的报错，返回相应错误页面
 * @author 偶是小菜鸟
 * Create Time: 2017-10-26 11:09
 */
@ControllerAdvice
public class BasicController {

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ModelAndView requestMissingServletRequest(MissingServletRequestParameterException ex, HttpSession session) {
        ModelAndView mav = new ModelAndView("error/400");
        ex.printStackTrace();
        return mav;
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ModelAndView methodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpSession session) {
        ModelAndView mav = new ModelAndView("error/405");
        ex.printStackTrace();
        return mav;
    }
}
