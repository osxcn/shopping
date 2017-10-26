package com.leaves.shopping.service;

import com.leaves.shopping.dto.ContentDto;
import com.leaves.shopping.model.Content;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 偶是小菜鸟
 */
public interface ContentService {

    /**
     * 获取所有商品列表
     * @return
     * @throws Exception
     */
    List<ContentDto> getContentList() throws Exception;

    /**
     * 根据用户ID查询用户购买过的商品列表
     * @param pid 用户ID
     * @return 已购买的商品列表
     * @throws Exception exception
     */
    List<ContentDto> getBuyContentList(Integer pid) throws Exception;

    /**
     * 根据用户ID查询用户未购买过的商品列表
     * @param pid 用户ID
     * @return 未购买的商品列表
     * @throws Exception exception
     */
    List<ContentDto> getNotBuyContentList(Integer pid) throws Exception;

    /**
     * 获取所有内容
     * @return 内容列表
     * @throws Exception exception
     */
    List<ContentDto> getAllContents() throws Exception;

    /**
     * 获取所有有销售记录的内容
     * @return 有销售记录的内容
     * @throws Exception exception
     */
    List<ContentDto> getIsSellContents() throws Exception;

    /**
     * 获取所有没有销售记录的内容
     * @return 没有销售记录的内容
     * @throws Exception exception
     */
    List<ContentDto> getIsNotSellContents() throws Exception;

    /**
     * 新增商品信息
     * @param content 商品信息
     * @return 商品信息
     * @throws Exception exception
     */
    ContentDto addContent(Content content) throws Exception;

    /**
     * 修改商品信息
     * @param content 商品信息
     * @return 商品信息
     * @throws Exception exception
     */
    ContentDto editContent(Content content) throws Exception;

    /**
     * 删除商品
     * @param cid 商品ID
     * @throws Exception exception
     */
    void deleteContent(Integer cid) throws Exception;

    /**
     * 切换商品状态
     * @param cid 商品ID
     * @throws Exception exception
     */
    void switchStatus(Integer cid) throws Exception;
}
