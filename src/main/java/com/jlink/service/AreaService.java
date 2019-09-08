package com.jlink.service;

import com.jlink.entity.Area;

import java.util.List;

public interface AreaService {

	public static final String AREALISTKEY = "arealist";
	
	/**
	 * 获取区域列表，优先从缓存读取
	 * @return
	 */
	List<Area> getAreaList();
}
