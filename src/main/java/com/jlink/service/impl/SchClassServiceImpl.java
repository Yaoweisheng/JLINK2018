package com.jlink.service.impl;

import com.jlink.dao.SchClassDao;
import com.jlink.entity.SchClass;
import com.jlink.service.SchClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SchClassServiceImpl implements SchClassService {

    @Autowired
    private SchClassDao schClassDao;

    @Override
    public List<SchClass> query(Integer page, Integer per) {
        return schClassDao.query((page-1)*per, per);
    }

    @Override
    public int count() {
        return schClassDao.count();
    }

    @Override
    public boolean save(SchClass schClass) {
        return schClassDao.save(schClass) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return schClassDao.delete(id) > 0;
    }

    @Override
    public boolean update(SchClass schClass) {
        return schClassDao.update(schClass) > 0;
    }

    @Override
    public SchClass getById(Integer id) {
        return schClassDao.getById(id);
    }
}
