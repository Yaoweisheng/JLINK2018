package com.jlink.dao;

import com.jlink.entity.Userinfo;

import java.util.List;

public interface UserinfoDao {
    int save(Userinfo userinfo);
    Userinfo getUserinfoByUserId(Integer userId);
    List<Userinfo> query(String badgenumber, String name, String phone, String deptNo, Integer offset, Integer limit);
    int getCount(String badgenumber, String name, String phone, String deptNo);
    int delete(Integer userId);
    int batchSave(List<Userinfo> userinfos);
}