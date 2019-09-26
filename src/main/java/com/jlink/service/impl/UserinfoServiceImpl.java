package com.jlink.service.impl;

import com.jlink.dao.UserinfoDao;
import com.jlink.entity.Userinfo;
import com.jlink.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    private UserinfoDao userinfoDao;
    @Override
    public boolean save(Userinfo userinfo) {
        return userinfoDao.save(userinfo) > 0;
    }

    @Override
    public Userinfo getUserinfoByUserId(Integer userId) {
        return userinfoDao.getUserinfoByUserId(userId);
    }

    @Override
    public List<Userinfo> query(String badgenumber, String name, String phone, String deptNo, Integer page, Integer per) {
        return userinfoDao.query(badgenumber, name, phone, deptNo, (page-1)*per, per);
    }

    @Override
    public int getCount(String badgenumber, String name, String phone, String deptNo) {
        return userinfoDao.getCount(badgenumber, name, phone, deptNo);
    }

    @Override
    public boolean delete(Integer userId) {
        return userinfoDao.delete(userId) > 0;
    }

    @Override
    public boolean batchSave(List<Userinfo> userinfos) {
        return userinfoDao.batchSave(userinfos) > 0;
    }
}
