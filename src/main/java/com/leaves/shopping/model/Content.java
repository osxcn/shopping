package com.leaves.shopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * content
 * 内容类
 * 
 * @author 偶是小菜鸟
 * @version 1.0
 */
@Table(name = "content")
public class Content {
	
    /**
     * content.id
     * 主键
     */
	@Id
	@Column(name = "id")
    private Integer id;

    /**
     * content.title
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * content.summary
     * 摘要
     */
    @Column(name = "summary")
    private String summary;

    /**
     * content.price
     * 价格
     */
    @Column(name = "price")
    private Double price;

    /**
     * content.salePrice
     * 促销价
     */
    @Column(name = "salePrice")
    private Double salePrice;

    /**
     * content.icon
     * 图片
     */
	@Column(name = "icon")
    private byte[] icon;

    /**
     * content.text
     * 正文
     */
	@Column(name = "text")
    private byte[] text;

    /**
     * content.status
     * 状态（1：未发布/下架，0：上架）
     */
    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}