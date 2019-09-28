package com.jlink.service;

import com.jlink.entity.SchClass;

import java.util.List;

public interface SchClassService {
    List<SchClass> query(Integer page, Integer per);
    int count();
    boolean save(SchClass schClass);
    boolean delete(Integer id);
    boolean update(SchClass schClass);
    SchClass getById(Integer id);
}
