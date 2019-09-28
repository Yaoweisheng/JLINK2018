package com.jlink.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NumRunDeil {
    private Short numRunid;

    private Date starttime;

    private Date endtime;

    private Short sdays;

    private Short edays;

    private Integer schclassid;

    private Integer overtime;
}