package com.Jlink.dao;

import com.Jlink.entity.Area;

import java.util.List;

public interface AreaDao {

	/**
	 * 列出区域列表
	 * @return areaList
	 */
	List<Area> queryArea();
}
