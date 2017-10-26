package com.leaves.shopping.controller;

import com.leaves.shopping.dto.ContentDto;
import com.leaves.shopping.mapper.ContentMapper;
import com.leaves.shopping.mapper.InventoryMapper;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.model.Inventory;
import com.leaves.shopping.model.Person;
import com.leaves.shopping.service.ContentService;
import com.leaves.shopping.service.InventoryService;
import com.leaves.shopping.util.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制类
 * @author 偶是小菜鸟
 */
@Controller
public class IndexController {

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private ContentService contentService;

    @Resource
    private InventoryService inventoryService;

    /**
     * TODO 跳转首页
     * @param map map
     * @param session session
     * @return 首页
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(
            @RequestParam(value = "type", defaultValue = "") String type,
            ModelMap map,
            HttpSession session
    ) throws Exception {
        String isBuy = "1";
        String isNotBuy = "2";
        String isSell = "1";
        String isNotSell = "2";
        try {
            if (session.getAttribute(Session.UserSession) != null) {
                // 获取用户类型
                Byte userType = ((Person) session.getAttribute(Session.UserSession)).getUsertype();
                // 获取用户ID
                Integer personId = ((Person) session.getAttribute(Session.UserSession)).getId();
                if (userType == 0) {
                    // 如果为买家
                    if ("".equals(type)) {
                        List<ContentDto> contentList = contentService.getContentList();
                        map.addAttribute("products", contentList);
                    }
                    else if (isBuy.equals(type)) {
                        // 获取用户已购买的商品列表
                        List<ContentDto> buyProducts = contentService.getBuyContentList(personId);
                        map.addAttribute("products", buyProducts);
                    }
                    else if (isNotBuy.equals(type)) {
                        // 获取用户未购买的商品列表
                        List<ContentDto> notBuyProducts = contentService.getNotBuyContentList(personId);
                        map.addAttribute("products", notBuyProducts);
                    }

                    return "index";
                } else {
                    // 如果为卖家
                    if("".equals(type)) {
                        List<ContentDto> allContents = contentService.getAllContents();
                        map.addAttribute("Contents", allContents);
                    }
                    else if (isSell.equals(type)) {
                        List<ContentDto> isSellContents = contentService.getIsSellContents();
                        map.addAttribute("Contents", isSellContents);
                    }
                    else if (isNotSell.equals(type)) {
                        List<ContentDto> isNotSellContents = contentService.getIsNotSellContents();
                        map.addAttribute("Contents", isNotSellContents);
                    }

                    return "sellerIndex";
                }
            } else {
                List<ContentDto> contentList = contentService.getContentList();
                map.addAttribute("products", contentList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * TODO 获取商品详情
     * @param id 商品ID
     * @param type 类型
     * @param map map
     * @return 商品详情页
     * @throws Exception exception
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(
            @RequestParam("id") Integer id,
            @RequestParam(value = "type", defaultValue = "") String type,
            ModelMap map
    ) throws Exception {
        // 获取商品信息
        Content content = contentMapper.selectByPrimaryKey(id);
        // 获取商品库存
        int inventory = inventoryService.getInventoryByCid(id);

        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setTitle(content.getTitle());
        contentDto.setSummary(content.getSummary());
        contentDto.setPrice(content.getPrice());
        contentDto.setSalePrice(content.getSalePrice());
        contentDto.setInventory(inventory);
        contentDto.setStatus(content.getStatus());
        byte[] icon = content.getIcon();
        if (icon != null) {
            contentDto.setImage(new String(icon, "utf-8"));
        }
        byte[] text = content.getText();
        if (text != null) {
            contentDto.setDetail(new String(text, "utf-8"));
        }

        map.addAttribute("product", contentDto);
        if("".equals(type)) {
            return "detail";
        } else {
            return "sellerDetail";
        }
    }
}
