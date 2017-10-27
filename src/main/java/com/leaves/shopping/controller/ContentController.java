package com.leaves.shopping.controller;

import com.leaves.shopping.dto.ContentDto;
import com.leaves.shopping.mapper.ContentMapper;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 偶是小菜鸟
 * CreateTime: 2017-10-24 22:48
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private ContentService contentService;

    /**
     * 跳转内容编辑页面
     * @param cid 内容ID
     * @param map map
     * @return 页面
     * @throws Exception exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(
            @RequestParam("id") Integer cid,
            ModelMap map
    ) throws Exception {
        // 获取商品信息
        Content content = contentMapper.selectByPrimaryKey(cid);

        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setTitle(content.getTitle());
        contentDto.setSummary(content.getSummary());
        contentDto.setPrice(content.getPrice());
        contentDto.setSalePrice(content.getSalePrice());
        byte[] icon = content.getIcon();
        if (icon != null) {
            contentDto.setImage(new String(icon, "utf-8"));
        }
        byte[] text = content.getText();
        if (text != null) {
            contentDto.setDetail(new String(text, "utf-8"));
        }

        map.addAttribute("product", contentDto);

        return "edit";
    }

    /**
     * 保存内容修改
     * @param id 内容ID
     * @param title 标题
     * @param summary 摘要
     * @param price 价格
     * @param salePrice 促销价
     * @param image 图片地址
     * @param detail 正文
     * @param map map
     * @return 内容
     * @throws Exception exception
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(
            @RequestParam("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("summary") String summary,
            @RequestParam("price") Double price,
            @RequestParam("salePrice") Double salePrice,
            @RequestParam("image") String image,
            @RequestParam("detail") String detail,
            ModelMap map
    ) throws Exception {
        try {
            Content content = new Content();
            content.setId(id);
            content.setTitle(title);
            content.setSummary(summary);
            content.setPrice(price);
            content.setSalePrice(salePrice);
            content.setIcon(image.getBytes("utf-8"));
            content.setText(detail.getBytes("utf-8"));
            ContentDto contentDto = contentService.editContent(content);

            map.addAttribute("product", contentDto);
            return "editSubmit";
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除内容
     * @param cid 内容ID
     * @return 删除结果
     * @throws Exception exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(
            @RequestParam("cid") Integer cid
    ) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            contentService.deleteContent(cid);

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

    /**
     * 切换内容状态
     * @param cid 内容ID
     * @return 切换结果
     * @throws Exception exception
     */
    @RequestMapping(value = "/switchStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object switchStatus(
            @RequestParam("cid") Integer cid
    ) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            contentService.switchStatus(cid);

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
