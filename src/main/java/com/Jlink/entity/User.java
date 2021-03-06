package com.Jlink.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author binzhang
 * @date 2019-05-08
 */
@Data
public class User {

    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String name;
    private String email;
    private String address;
    private Integer status;
    private Integer roleId;
    private Date createTime;

}
