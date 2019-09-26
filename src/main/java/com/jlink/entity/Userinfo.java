package com.jlink.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Userinfo {
    private Integer userid;

    private String badgenumber;

    private String ssn;

    private String name;

    private String gender;

    private String title;

    private String pager;

    private Date birthday;

    private Date hiredday;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String ophone;

    private String fphone;

    private Short att;

    private Short inlate;

    private Short outearly;

    private Short overtime;

    private Short sep;

    private Short holiday;

    private String minzu;

    private String cardno;

    private String deptNo;

    private Date leavedate;

    private Integer defaultdeptid;
}