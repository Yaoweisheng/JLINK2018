package com.jlink.service;

import com.jlink.entity.UserinfoOfRun;

import java.util.List;

public interface UserinfoOfRunService {
    List<UserinfoOfRun> query(Integer numRunId, String deptNo, String name, Integer Page, Integer per);
    int count(Integer numRunId, String deptNo, String name);
    boolean batchSave(List<UserinfoOfRun> list);
    boolean batchDelete(List<UserinfoOfRun> list);
    List<UserinfoOfRun> getByUserinfoId(Integer userinfoId);
}
