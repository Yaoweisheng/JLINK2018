package com.jlink.service;

import com.jlink.entity.NumRun;

import java.util.List;

public interface NumRunService {
    List<NumRun> query(Integer page, Integer per);
    int count();
    boolean delete(Integer id);
    boolean save(NumRun numRun);
    boolean update(NumRun numRun);
}
