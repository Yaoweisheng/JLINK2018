package com.jlink.dao;

import com.jlink.entity.Area;

import java.util.List;

public interface AreaDao {
    List<Area> getProvinces();
    List<Area> getCitys(String provinceCode);
    List<Area> getAreas(String cityCode);
}