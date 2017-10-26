package com.leaves.shopping.controller;

import com.leaves.shopping.mapper.InventoryMapper;
import com.leaves.shopping.model.Inventory;
import com.leaves.shopping.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 偶是小菜鸟
 * CreateTime: 2017-10-24 22:25
 */
@Controller
public class SellerController {

    @Resource
    private InventoryMapper inventoryMapper;


    /**
     * 进货
     * @param cid 内容ID
     * @param num 进货数量
     * @return 进货结果
     * @throws Exception exception
     */
    @RequestMapping(value = "/inventory/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addInventory(
            @RequestParam("cid") Integer cid,
            @RequestParam("num") Integer num
    ) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        try {
            Inventory inventory = new Inventory();
            inventory.setCid(cid);
            inventory.setNum(num);
            inventoryMapper.insertSelective(inventory);

            result.put("code", 200);
            result.put("message","success");
            result.put("result", true);
            return result;
        } catch (Exception e) {
            result.put("code", 201);
            result.put("message","进货失败");
            result.put("result", false);
            return result;
        }
    }


}
