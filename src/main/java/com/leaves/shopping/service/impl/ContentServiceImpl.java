package com.leaves.shopping.service.impl;

import com.leaves.shopping.dto.ContentDto;
import com.leaves.shopping.mapper.ContentMapper;
import com.leaves.shopping.mapper.InventoryMapper;
import com.leaves.shopping.mapper.TrxMapper;
import com.leaves.shopping.model.Content;
import com.leaves.shopping.model.Inventory;
import com.leaves.shopping.service.ContentService;
import com.leaves.shopping.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 偶是小菜鸟
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentServiceImpl implements ContentService {

    @Resource
    private TrxMapper trxMapper;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private InventoryService inventoryService;

    private String statusOn = "0";

    private String statusUn = "1";

    @Override
    public List<ContentDto> getContentList() throws Exception {
        Example example = new Example(Content.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("status", "0");
        List<Content> contents = contentMapper.selectByExample(example);
        return getContentList(contents);
    }

    @Override
    public List<ContentDto> getBuyContentList(Integer pid) throws Exception {
        List<Integer> cidList = trxMapper.getBuyContentIdList(pid);
        if(cidList.size() == 0) {
            return null;
        }
        Example example = new Example(Content.class);
        Example.Criteria c = example.createCriteria();
        c.andIn("id", cidList);
        List<Content> contents = contentMapper.selectByExample(example);

        return getContentList(contents);
    }

    @Override
    public List<ContentDto> getNotBuyContentList(Integer pid) throws Exception {
        List<Integer> cidList = trxMapper.getBuyContentIdList(pid);
        if(cidList.size() == 0) {
            return getContentList();
        }
        Example example = new Example(Content.class);
        Example.Criteria c = example.createCriteria();
        c.andNotIn("id", cidList);
        List<Content> contents = contentMapper.selectByExample(example);

        return getContentList(contents);
    }

    @Override
    public List<ContentDto> getAllContents() throws Exception {
        List<ContentDto> contentList = new ArrayList<>(16);
        // 获取所有内容列表
        List<Content> contents = contentMapper.selectAll();
        // 获取有销售记录的内容ID列表
        List<Integer> cidList = trxMapper.getIsSellContentIdList();
        for(Content content : contents) {
            ContentDto contentDto = new ContentDto();
            contentDto.setId(content.getId());
            byte[] icon = content.getIcon();
            if (icon != null) {
                contentDto.setImage(new String(icon, "utf-8"));
            }
            contentDto.setTitle(content.getTitle());
            contentDto.setSalePrice(content.getSalePrice());
            contentDto.setInventory(inventoryService.getInventoryByCid(content.getId()));
            contentDto.setStatus(content.getStatus());
            for (Integer cid: cidList) {
                if(cid.equals(content.getId())) {
                    contentDto.setIsSell(true);
                }
            }
            contentList.add(contentDto);
        }

        return contentList;
    }

    @Override
    public List<ContentDto> getIsSellContents() throws Exception {
        List<ContentDto> contentList = new ArrayList<>(16);
        // 获取有销售记录的内容ID列表
        List<Integer> cidList = trxMapper.getIsSellContentIdList();
        if(cidList.size() == 0) {
            return null;
        }
        for(Integer cid : cidList) {
            Content content = contentMapper.selectByPrimaryKey(cid);
            ContentDto contentDto = new ContentDto();
            contentDto.setId(content.getId());
            byte[] icon = content.getIcon();
            if (icon != null) {
                contentDto.setImage(new String(icon, "utf-8"));
            }
            contentDto.setTitle(content.getTitle());
            contentDto.setSalePrice(content.getSalePrice());
            contentDto.setInventory(inventoryService.getInventoryByCid(content.getId()));
            contentDto.setStatus(content.getStatus());
            contentDto.setIsSell(true);
            contentList.add(contentDto);
        }

        return contentList;
    }

    @Override
    public List<ContentDto> getIsNotSellContents() throws Exception {
        // 获取有销售记录的内容ID列表
        List<Integer> cidList = trxMapper.getIsSellContentIdList();
        // 如果没有已销售的内容，则返回所有内容
        if(cidList.size() == 0) {
            return getAllContents();
        }
        // 获取未销售的所有内容
        Example example = new Example(Content.class);
        Example.Criteria c = example.createCriteria();
        c.andNotIn("id", cidList);
        List<Content> contents = contentMapper.selectByExample(example);

        return getContentList(contents);
    }

    @Override
    public ContentDto addContent(Content content) throws Exception {
        // 保存内容
        contentMapper.insertSelective(content);
        content = contentMapper.selectOne(content);

        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setTitle(content.getTitle());
        contentDto.setSummary(content.getSummary());
        contentDto.setPrice(content.getPrice());
        contentDto.setSalePrice(content.getSalePrice());
        contentDto.setStatus(content.getStatus());
        contentDto.setImage(new String(content.getIcon(), "utf-8"));
        contentDto.setDetail(new String(content.getText(), "utf-8"));

        return contentDto;
    }

    @Override
    public ContentDto editContent(Content content) throws Exception {
        // 保存修改
        contentMapper.updateByPrimaryKeySelective(content);
        // 读取商品信息
        content = contentMapper.selectByPrimaryKey(content.getId());
        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setTitle(content.getTitle());
        contentDto.setSummary(content.getSummary());
        contentDto.setPrice(content.getPrice());
        contentDto.setSalePrice(content.getSalePrice());
        contentDto.setImage(new String(content.getIcon(), "utf-8"));
        contentDto.setDetail(new String(content.getText(), "utf-8"));

        return contentDto;
    }

    @Override
    public void deleteContent(Integer cid) throws Exception {
        // 删除库存
        Example example = new Example(Inventory.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("cid", cid);
        inventoryMapper.deleteByExample(example);
        // 删除商品
        contentMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public void switchStatus(Integer cid) throws Exception {
        Content content = contentMapper.selectByPrimaryKey(cid);
        String status = content.getStatus();
        if(statusOn.equals(status)) {
            status = statusUn;
        } else {
            status = statusOn;
        }
        content.setStatus(status);
        contentMapper.updateByPrimaryKeySelective(content);
    }

    /**
     * 将内容转化为ContentDto
     * @param contents 内容列表
     * @return 内容列表
     * @throws Exception exception
     */
    private List<ContentDto> getContentList(List<Content> contents) throws Exception {
        List<ContentDto> contentList = new ArrayList<>(16);

        for(Content content : contents) {
            ContentDto contentDto = new ContentDto();
            contentDto.setId(content.getId());
            byte[] icon = content.getIcon();
            if (icon != null) {
                contentDto.setImage(new String(icon, "utf-8"));
            }
            contentDto.setTitle(content.getTitle());
            contentDto.setSalePrice(content.getSalePrice());
            contentDto.setInventory(inventoryService.getInventoryByCid(content.getId()));
            contentDto.setStatus(content.getStatus());
            contentList.add(contentDto);
        }

        return contentList;
    }
}
