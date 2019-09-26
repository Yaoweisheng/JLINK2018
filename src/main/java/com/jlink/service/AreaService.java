package com.jlink.service;

import com.jlink.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> getProvinces();
    List<Area> getCitys(String provinceCode);
    List<Area> getAreas(String cityCode);
}
