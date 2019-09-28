package com.jlink.dao;

import com.jlink.entity.SchClass;

import java.util.List;

public interface SchClassDao {
    List<SchClass> query(Integer offset, Integer limit);
    int count();
    int save(SchClass schClass);
    int delete(Integer id);
    int update(SchClass schClass);
    SchClass getById(Integer id);
}