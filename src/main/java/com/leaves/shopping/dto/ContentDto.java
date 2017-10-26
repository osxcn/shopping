package com.leaves.shopping.dto;

import com.leaves.shopping.model.Content;

/**
 * @author 偶是小菜鸟
 */
public class ContentDto extends Content {

    /**
     * 图片
     */
    private String image;

    /**
     * 正文
     */
    private String detail;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 是否有销售记录
     */
    private Boolean isSell = false;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(Boolean isSell) {
        this.isSell = isSell;
    }
}
