package com.leaves.shopping.controller;

import com.leaves.shopping.mapper.PersonMapper;
import com.leaves.shopping.model.Person;
import com.leaves.shopping.util.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 * @author 偶是小菜鸟
 */
@Controller
public class LoginController {

    @Resource
    private PersonMapper personMapper;

    /**
     * 跳转登录页面
     * @return 登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @param session session
     * @return 登录结果
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public Object userLogin(
            @Param("userName") String userName,
            @Param("password") String password,
            HttpSession session
    ) {
        Map<String, Object> result = new HashMap<>(16);

        Person person = new Person();
        person.setUsername(userName);
        person.setPassword(password);
        person = personMapper.selectOne(person);
        if(person != null) {
            session.setAttribute("user", person);
            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
        } else {
            result.put("code", 201);
            result.put("message","登录失败");
            result.put("result", false);
        }

        return result;
    }

    /**
     * 退出登录
     * @param session session
     * @return 登录页面
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    /**
     * 判断是否登录
     * @param session session
     * @return 判断结果
     */
    @RequestMapping(value = "/api/isLogin", method = RequestMethod.GET)
    public Object isLogin(HttpSession session) {
        Map<String, Object> result = new HashMap<>(16);
        if (session.getAttribute(Session.UserSession) != null) {
            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
        } else {
            result.put("code", 201);
            result.put("message","failed");
            result.put("result", false);
        }

        return result;
    }

}
