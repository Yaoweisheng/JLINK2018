package com.jlink.service;

import com.jlink.entity.Userinfo;

import java.util.List;

public interface UserinfoService {
    boolean save(Userinfo userinfo);
    Userinfo getUserinfoByUserId(Integer userId);
    List<Userinfo> query(String badgenumber, String name, String phone, String deptNo, Integer page, Integer per);
    int getCount(String badgenumber, String name, String phone, String deptNo);
    boolean delete(Integer userId);
    boolean batchSave(List<Userinfo> userinfos);
}
