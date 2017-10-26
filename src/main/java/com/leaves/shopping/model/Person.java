package com.leaves.shopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * person
 * 用户类
 * 
 * @author 偶是小菜鸟
 * @version 1.0
 */
@Table(name = "person")
public class Person {
	
    /**
     * person.id
     * 主键
     */
	@Id
	@Column(name = "id")
    private Integer id;

    /**
     * person.userName
     * 用户名
     */
	@Column(name = "username")
    private String username;

    /**
     * person.password
     * 密码md5加密
     */
	@Column(name = "password")
    private String password;

    /**
     * person.nickName
     * 用户昵称
     */
	@Column(name = "nickname")
    private String nickname;

    /**
     * person.userType
     * 类型，买家0，卖家1
     */
	@Column(name = "usertype")
    private Byte usertype;

    /**
     * 获取主键
     * 这个方法返回了数据表字段 person.id
     *
     * @return 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     * 这个方法设置数据表字段 person.id
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     * 这个方法返回了数据表字段 person.userName
     *
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * 这个方法设置数据表字段 person.userName
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码md5加密
     * 这个方法返回了数据表字段 person.password
     *
     * @return 密码md5加密
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码md5加密
     * 这个方法设置数据表字段 person.password
     *
     * @param password 密码md5加密
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户昵称
     * 这个方法返回了数据表字段 person.nickName
     *
     * @return 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     * 这个方法设置数据表字段 person.nickName
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取类型，买家0，卖家1
     * 这个方法返回了数据表字段 person.userType
     *
     * @return 类型，买家0，卖家1
     */
    public Byte getUsertype() {
        return usertype;
    }

    /**
     * 设置类型，买家0，卖家1
     * 这个方法设置数据表字段 person.userType
     *
     * @param usertype 类型，买家0，卖家1
     */
    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }
}