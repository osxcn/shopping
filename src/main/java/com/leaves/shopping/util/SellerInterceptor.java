package com.leaves.shopping.util;

import com.leaves.shopping.model.Person;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 卖家页面拦截
 * 未登录情况下除首页、商品详情页外不可登录
 * <br>
 * 买家页面不可登录
 * @author 偶是小菜鸟
 * Create Time: 2017-10-26 10:20
 */
public class SellerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Person currentPerson = (Person) httpServletRequest.getSession().getAttribute("user");
        if (currentPerson == null) {
            httpServletResponse.sendRedirect("/login");
            return false;
        } else if (!currentPerson.getUsertype().equals((byte)1)) {
            httpServletResponse.sendRedirect("/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
