package com.leaves.shopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * inventory
 * 库存类
 *
 * @author 偶是小菜鸟
 * @version 1.0
 */
@Table(name = "inventory")
public class Inventory {

    /**
     * inventory.id
     * 主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * inventory.cid
     * 商品ID
     */
    @Column(name = "cid")
    private Integer cid;

    /**
     * inventory.num
     * 进货数量
     */
    @Column(name = "num")
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
