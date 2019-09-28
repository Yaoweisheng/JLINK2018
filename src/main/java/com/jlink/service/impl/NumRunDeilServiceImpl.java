package com.jlink.service.impl;

import com.jlink.dao.NumRunDeilDao;
import com.jlink.entity.NumRun;
import com.jlink.entity.NumRunDeil;
import com.jlink.service.NumRunDeilService;
import com.jlink.service.NumRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumRunDeilServiceImpl implements NumRunDeilService {
    @Autowired
    NumRunDeilDao numRunDeilDao;

    @Override
    public boolean save(NumRunDeil numRunDeil) {
        return numRunDeilDao.save(numRunDeil) > 0;
    }

    @Override
    public boolean delete(NumRunDeil numRunDeil) {
        return numRunDeilDao.delete(numRunDeil) > 0;
    }

    @Override
    public List<NumRunDeil> query(Integer id, Integer page, Integer per) {
        return numRunDeilDao.query(id,(page-1)*per, per);
    }

    @Override
    public int count(Integer id) {
        return numRunDeilDao.count(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return numRunDeilDao.deleteById(id) > 0;
    }
}
