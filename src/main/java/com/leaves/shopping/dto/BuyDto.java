package com.leaves.shopping.dto;

import com.leaves.shopping.model.Trx;

/**
 * @author 偶是小菜鸟
 */
public class BuyDto extends Trx {

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 图片
     */
    private String image;

    /**
     * 正文
     */
    private String detail;

    /**
     * 是否已经购买
     */
    private Boolean isBuy = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

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

    public Boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Boolean buy) {
        isBuy = buy;
    }
}
