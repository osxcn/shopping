package com.leaves.shopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * trx
 * 交易记录类
 * 
 * @author 偶是小菜鸟
 * @version 1.0
 */
@Table(name = "trx")
public class Trx {
	
    /**
     * trx.id
     * 主键
     */
	@Id
	@Column(name = "id")
    private Integer id;

    /**
     * trx.contentId
     * 商品ID
     */
	@Column(name = "contentId")
    private Integer contentId;

    /**
     * trx.personId
     * 用户ID
     */
	@Column(name = "personId")
    private Integer personId;

    /**
     * trx.price
     * 购买单价
     */
	@Column(name = "price")
    private Double price;

    /**
     * trx.num
     * 购买数量
     */
    @Column(name = "num")
    private Integer num;

    /**
     * trx.payment
     * 支付金额
     */
    @Column(name = "payment")
    private Double payment;

    /**
     * trx.time
     * 购买时间
     */
	@Column(name = "time")
    private Long time;

    /**
     * 获取主键
     * 这个方法返回了数据表字段 trx.id
     *
     * @return 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     * 这个方法设置数据表字段 trx.id
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取内容ID
     * 这个方法返回了数据表字段 trx.contentId
     *
     * @return 内容ID
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * 设置内容ID
     * 这个方法设置数据表字段 trx.contentId
     *
     * @param contentId 内容ID
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取用户ID
     * 这个方法返回了数据表字段 trx.personId
     *
     * @return 用户ID
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * 设置用户ID
     * 这个方法设置数据表字段 trx.personId
     *
     * @param personId 用户ID
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * 获取购买单价
     * 这个方法返回了数据表字段 trx.price
     *
     * @return 购买单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置购买单价
     * 这个方法设置数据表字段 trx.price
     *
     * @param price 购买单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取购买数量
     * 这个方法返回了数据表字段 trx.num
     *
     * @return 购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置购买数量
     * 这个方法设置数据表字段 trx.num
     *
     * @param num 购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取支付金额
     * 这个方法返回了数据表字段 trx.payment
     *
     * @return 支付金额
     */
    public Double getPayment() {
        return payment;
    }

    /**
     * 设置支付金额
     * 这个方法设置数据表字段 trx.payment
     *
     * @param payment 支付金额
     */
    public void setPayment(Double payment) {
        this.payment = payment;
    }

    /**
     * 获取购买时间
     * 这个方法返回了数据表字段 trx.time
     *
     * @return 购买时间
     */
    public Long getTime() {
        return time;
    }

    /**
     * 设置购买时间
     * 这个方法设置数据表字段 trx.time
     *
     * @param time 购买时间
     */
    public void setTime(Long time) {
        this.time = time;
    }
}