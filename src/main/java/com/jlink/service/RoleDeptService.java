package com.jlink.service;

import com.jlink.entity.RoleDept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleDeptService {

    int save(RoleDept roleDept);

    int batchSave(List<String> deptNos, Integer roleId);

    int deleteByRoleId(Integer roleId);

    List<String> getDeptsByRoleId(Integer roleId);
}
