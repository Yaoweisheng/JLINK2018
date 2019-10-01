package com.jlink.service.impl;

import com.jlink.dao.UserinfoOfRunDao;
import com.jlink.entity.UserinfoOfRun;
import com.jlink.service.UserinfoOfRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoOfRunServiceImpl implements UserinfoOfRunService {
    @Autowired
    UserinfoOfRunDao userinfoOfRunDao;
    @Override
    public List<UserinfoOfRun> query(Integer numRunId, String deptNo, String name, Integer page, Integer per) {
        return userinfoOfRunDao.query(numRunId, deptNo, name, (page-1)*per, per);
    }

    @Override
    public int count(Integer numRunId, String deptNo, String name) {
        return userinfoOfRunDao.count(numRunId, deptNo, name);
    }

    @Override
    public boolean batchSave(List<UserinfoOfRun> list) {
        return userinfoOfRunDao.batchSave(list) > 0;
    }

    @Override
    public boolean batchDelete(List<UserinfoOfRun> list) {
        return userinfoOfRunDao.batchDelete(list) > 0;
    }

    @Override
    public List<UserinfoOfRun> getByUserinfoId(Integer userinfoId) {
        return userinfoOfRunDao.getByUserinfoId(userinfoId);
    }
}
