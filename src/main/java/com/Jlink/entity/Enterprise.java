package com.Jlink.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class Enterprise {
    private Integer enterpriseId;
    private String enterpriseName;
    private String enterpriseNameEnglish;
    private String enterpriseType;
    private String enterpriseCode;
    private String legelPerson;
    private Date startTime;
    private String licenceImg;
    private Date validTime;
    private Date createTime;
    private String address;
    private Long userId;
}
