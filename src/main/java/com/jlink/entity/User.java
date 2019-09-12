package com.jlink.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author binzhang
 * @date 2019-05-08
 */
@Data
public class User {

    public User(){}

    private Integer userId;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private Date modifyTime;
    private Integer isDelete;
    private Integer isActive;

}
