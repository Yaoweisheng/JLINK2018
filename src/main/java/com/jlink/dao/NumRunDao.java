package com.jlink.dao;

import com.jlink.entity.NumRun;

import java.util.List;

public interface NumRunDao {
    List<NumRun> query(Integer offset, Integer limit);
    int count();
    int delete(Integer id);
    int save(NumRun numRun);
    int update(NumRun numRun);
}