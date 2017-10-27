package com.leaves.shopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Temporary
 * 购物车类
 *
 * @author 偶是小菜鸟
 * @version 1.0
 */
@Table(name = "temporary")
public class Temporary {

    /**
     * temporary.id
     * 主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * temporary.pid
     * 用户ID
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * temporary.cid
     * 商品ID
     */
    @Column(name = "cid")
    private Integer cid;

    /**
     * temporary.num
     * 购买数量
     */
    @Column(name = "num")
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
