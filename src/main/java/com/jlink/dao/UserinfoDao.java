package com.jlink.dao;

import com.jlink.entity.Userinfo;

public interface UserinfoDao {
    int insert(Userinfo record);

    int insertSelective(Userinfo record);
}