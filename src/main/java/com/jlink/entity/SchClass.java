package com.jlink.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SchClass {
    private Integer id;

    private String schName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone="GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    private Integer lateMinutes;

    private Integer earlyMinutes;

    private Integer checkIn;

    private Integer checkOut;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone="GMT+8")
    private Date checkInTime1;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone="GMT+8")
    private Date checkInTime2;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone="GMT+8")
    private Date checkOutTime1;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone="GMT+8")
    private Date checkOutTime2;

    private Integer color;

    private Short autoBind;

    private Double workDay;

    private String sensorId;

    private Double workMins;
}