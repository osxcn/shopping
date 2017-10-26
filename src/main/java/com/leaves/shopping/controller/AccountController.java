package com.leaves.shopping.controller;

import com.leaves.shopping.model.Person;
import com.leaves.shopping.service.AccountService;
import com.leaves.shopping.util.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 财务相关
 * @author 偶是小菜鸟
 */
@Controller
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 跳转用户财务页面
     * @param map map
     * @param session session
     * @return 财务页面
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(
            ModelMap map,
            HttpSession session
    ) throws Exception {
        Integer personId = ((Person) session.getAttribute(Session.UserSession)).getId();
        map.addAttribute("buyList", accountService.buyList(personId));
        return "account";
    }

    /**
     * 跳转已购商品详情页面
     * @param id 购买记录ID
     * @param map map
     * @return 已购商品详情页面
     */
    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public String snapshot(
            @RequestParam("id") Integer id,
            ModelMap map
    ) throws Exception {
        try {
            map.addAttribute("product", accountService.getContentInfoByTid(id));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "snapshot";
    }
}
