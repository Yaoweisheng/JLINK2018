package com.jlink.service.impl;

import com.jlink.dao.NumRunDao;
import com.jlink.entity.NumRun;
import com.jlink.service.NumRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumRunServiceImpl implements NumRunService {

    @Autowired
    private NumRunDao numRunDao;

    @Override
    public List<NumRun> query(Integer page, Integer per) {
        return numRunDao.query((page-1)*per, per);
    }

    @Override
    public int count() {
        return numRunDao.count();
    }

    @Override
    public boolean delete(Integer id) {
        return numRunDao.delete(id) > 0;
    }

    @Override
    public boolean save(NumRun numRun) {
        return numRunDao.save(numRun) > 0;
    }

    @Override
    public boolean update(NumRun numRun) {
        return numRunDao.update(numRun) > 0;
    }
}
