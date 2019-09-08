package com.jlink.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Department {
    public Department(){}
    public Department(String deptNo, String deptName, String deptAddr){
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.deptAddr = deptAddr;
    }

    private Integer deptId;

    private String deptNo;

    private String deptName;

    private String deptAddr;

    private Integer createId;

    private Date createDate;

    private Integer modifyId;

    private Date modifyDate;
}