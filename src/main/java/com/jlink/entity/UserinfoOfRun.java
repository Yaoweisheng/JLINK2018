package com.jlink.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserinfoOfRun {
    private Integer userinfoId;

    private Integer numRunId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startdate;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date enddate;

    private Integer isNotOfRun;

    private Integer orderRun;
}