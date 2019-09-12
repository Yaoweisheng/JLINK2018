package com.jlink.dao;

import com.jlink.entity.Area;

public interface AreaDao {
    int insert(Area record);

    int insertSelective(Area record);
}