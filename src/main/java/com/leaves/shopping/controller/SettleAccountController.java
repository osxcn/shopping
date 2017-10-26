package com.leaves.shopping.controller;

import com.leaves.shopping.dto.SettleAccountDto;
import com.leaves.shopping.mapper.ContentMapper;
import com.leaves.shopping.mapper.TemporaryMapper;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.model.Person;
import com.leaves.shopping.model.Temporary;
import com.leaves.shopping.service.SettleAccountService;
import com.leaves.shopping.util.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 购物控制类
 * @author 偶是小菜鸟
 */
@Controller
@RequestMapping("/settleAccount")
public class SettleAccountController {

    @Resource
    private TemporaryMapper temporaryMapper;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SettleAccountService settleAccountService;

    /**
     * 查看购物车
     * @param map map
     * @param session session
     * @return 购物车页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(
            ModelMap map,
            HttpSession session
    ) throws Exception {
        List<SettleAccountDto> sadList = new ArrayList<>();

        // 获取用户ID
        Integer personId = ((Person) session.getAttribute(Session.UserSession)).getId();
        // 根据用户ID获取用户购物车信息
        Example example = new Example(Temporary.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("pid", personId);
        List<Temporary> temporaryList = temporaryMapper.selectByExample(example);
        for(Temporary temporary : temporaryList) {
            SettleAccountDto sad = new SettleAccountDto();
            Content content = contentMapper.selectByPrimaryKey(temporary.getCid());
            // 计算支付金额
            Double price = content.getSalePrice();
            Integer num = temporary.getNum();
            Double payment = price * num;

            sad.setId(temporary.getId());
            sad.setCid(temporary.getCid());
            byte[] icon = content.getIcon();
            if (icon != null) {
                sad.setImage(new String(icon, "utf-8"));
            }
            sad.setTitle(content.getTitle());
            sad.setPrice(price);
            sad.setNum(num);
            sad.setPayment(payment);
            sad.setStatus(content.getStatus());

            sadList.add(sad);
        }

        map.addAttribute("settleAccountList", sadList);

        return "settleAccount";
    }

    /**
     * 加入购物车
     * @param cid 商品ID
     * @param num 商品数量
     * @param session session
     * @return 加入结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addSettleAccount(
            @RequestParam("cid") Integer cid,
            @RequestParam("num") Integer num,
            HttpSession session
    ) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        try {
            // 获取用户ID
            Integer personId = ((Person) session.getAttribute(Session.UserSession)).getId();
            // 查询购物车中是否含有同种商品
            Temporary temporary = new Temporary();
            temporary.setCid(cid);
            temporary.setPid(personId);
            temporary = temporaryMapper.selectOne(temporary);
            if(temporary != null) {
                // 如果含有同种商品，则更新数量，数量为购物车中数量加新增数量
                int tnum = temporary.getNum();
                temporary.setNum(tnum + num);
                temporaryMapper.updateByPrimaryKeySelective(temporary);
            } else {
                // 如果购物车中没有该商品，则新增购物车记录
                temporary = new Temporary();
                temporary.setCid(cid);
                temporary.setPid(personId);
                temporary.setNum(num);
                temporaryMapper.insertSelective(temporary);
            }
            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
            return result;
        } catch (Exception e) {
            result.put("code", 201);
            result.put("message","加入购物车失败");
            result.put("result", false);
            return result;
        }
    }

    /**
     * 根据购物车ID修改购物车商品数量
     * @param id 购物车ID
     * @param num 商品数量
     * @return 修改结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object editSettleAccount(
            @RequestParam("id") Integer id,
            @RequestParam("num") Integer num
    ) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        try {
            Temporary temporary = temporaryMapper.selectByPrimaryKey(id);
            temporary.setNum(num);
            temporaryMapper.updateByPrimaryKeySelective(temporary);

            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
            return result;
        } catch (Exception e) {
            result.put("code", 201);
            result.put("message","修改数量失败");
            result.put("result", false);
            return result;
        }
    }

    /**
     * 删除购物车记录
     * @param id 购物车ID
     * @return 删除结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(
            @RequestParam("id") Integer id
    ) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        try {
            temporaryMapper.deleteByPrimaryKey(id);

            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
            return result;
        } catch (Exception e) {
            result.put("code", 201);
            result.put("message","修改数量失败");
            result.put("result", false);
            return result;
        }
    }

    /**
     * 购买内容
     * @param tidList 购物车ID列表
     * @param cid 内容ID
     * @param num 购买数量
     * @param session session
     * @return 购买结果
     * @throws Exception exception
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public Object buy(
            @RequestParam(value = "tidList", defaultValue = "") String tidList,
            @RequestParam(value = "cid", defaultValue = "") Integer cid,
            @RequestParam(value = "num", defaultValue = "") Integer num,
            HttpSession session
    ) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        try {
            // 获取用户ID
            Integer personId = ((Person) session.getAttribute(Session.UserSession)).getId();
            if(!"".equals(tidList)) {
                // 购物车购买
                StringTokenizer tmp = new StringTokenizer(tidList, ",");
                while ( tmp.hasMoreElements() ){
                    settleAccountService.buyFromTemporary(personId, Integer.parseInt(tmp.nextToken()));
                }
            } else {
                // 直接购买
                settleAccountService.buy(personId, cid, num);
            }

            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
            return result;
        } catch (Exception e) {
            result.put("code", 201);
            result.put("message","购买失败");
            result.put("result", false);
            return result;
        }
    }
}
