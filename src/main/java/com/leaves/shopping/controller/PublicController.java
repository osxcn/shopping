package com.leaves.shopping.controller;

import com.leaves.shopping.dto.ContentDto;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.service.ContentService;
import com.leaves.shopping.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 偶是小菜鸟
 * Create Time: 2017-10-25 11:38
 */
@Controller
public class PublicController {

    @Resource
    private ContentService contentService;

    /**
     * 跳转发布页面
     * @return 发布页面
     */
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String pul() {
        return "public";
    }

    /**
     * 发布内容
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
    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(
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
            content.setTitle(title);
            content.setSummary(summary);
            content.setPrice(price);
            content.setSalePrice(salePrice);
            content.setIcon(image.getBytes("utf-8"));
            content.setText(detail.getBytes("utf-8"));
            ContentDto contentDto = contentService.addContent(content);

            map.addAttribute("product", contentDto);
            return "publicSubmit";
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 上传图片
     * @param request request
     * @return 上传结果
     * @throws Exception exception
     */
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 将文件放置位置设置为静态资源目录image中
            String logoPathDir = request.getSession().getServletContext().getRealPath("/") +"/image/";
            File logoSaveFile = new File(logoPathDir);
            if (!logoSaveFile.exists()){
                logoSaveFile.mkdirs();
            }
            // 页面控件的文件流
            MultipartFile multipartFile = multipartRequest.getFile("file");
            // 获取文件的后缀
            String suffix = multipartFile.getOriginalFilename().substring(
                    multipartFile.getOriginalFilename().lastIndexOf("."));
            // 使用UUID生成文件名称
            String imageName = UUID.randomUUID().toString() + suffix;

            // 拼成完整的文件保存路径加文件
            String fileName = logoPathDir + imageName;
            File file = new File(fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 生成图片网址
            String imgUrl = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/image/" + imageName;

            return Result.returnResult(imgUrl);
        } catch (Exception e) {
            return Result.failed();
        }
    }
}
