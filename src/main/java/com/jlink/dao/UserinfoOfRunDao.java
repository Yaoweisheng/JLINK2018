package com.jlink.dao;

import com.jlink.entity.UserinfoOfRun;

import java.util.List;

public interface UserinfoOfRunDao {
    List<UserinfoOfRun> query(Integer numRunId, String deptNo, String name, Integer offset, Integer limit);
    int count(Integer numRunId, String deptNo, String name);
    int batchSave(List<UserinfoOfRun> list);
    int batchDelete(List<UserinfoOfRun> list);
    List<UserinfoOfRun> getByUserinfoId(Integer userinfoId);
}