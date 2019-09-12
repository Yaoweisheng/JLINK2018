package com.jlink.dao;

import com.jlink.entity.RoleDept;

import java.util.List;

public interface RoleDeptDao {
    int save(RoleDept roleDept);

    int batchSave(List<String> deptNos, Integer roleId);

    int deleteByRoleId(Integer roleId);

    List<String> getDeptsByRoleId(Integer roleId);

}