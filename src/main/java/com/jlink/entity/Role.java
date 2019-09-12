package com.jlink.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yaoweisheng
 * @date 2019-09-10
 */
@Data
public class Role {
    private Integer roleId;
    private String name;
    private String code;
    private Date createTime;
    private Date modifyTime;
    private Integer isDelete;
    private Integer isActive;
}
