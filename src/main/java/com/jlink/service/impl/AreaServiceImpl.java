package com.jlink.service.impl;

import com.jlink.dao.AreaDao;
import com.jlink.entity.Area;
import com.jlink.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaDao areaDao;
    @Override
    public List<Area> getProvinces() {
        return areaDao.getProvinces();
    }

    @Override
    public List<Area> getCitys(String provinceCode) {
        return areaDao.getCitys(provinceCode);
    }

    @Override
    public List<Area> getAreas(String cityCode) {
        return areaDao.getAreas(cityCode);
    }
}
