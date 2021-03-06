package com.Jlink.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Area {

	//ID
	private Long areaId;
	//名称
	private String areaName;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEditTime;
	
}
