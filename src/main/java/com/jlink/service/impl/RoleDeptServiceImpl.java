package com.jlink.service.impl;

import com.jlink.dao.RoleDeptDao;
import com.jlink.entity.RoleDept;
import com.jlink.service.RoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDeptServiceImpl implements RoleDeptService {

    @Autowired
    RoleDeptDao roleDeptDao;
    @Override
    public int save(RoleDept roleDept) {
        return roleDeptDao.save(roleDept);
    }

    @Override
    public int batchSave(List<String> deptNos, Integer roleId) {
        return roleDeptDao.batchSave(deptNos, roleId);
    }

    @Override
    public int deleteByRoleId(Integer roleId) {
        return roleDeptDao.deleteByRoleId(roleId);
    }

    @Override
    public List<String> getDeptsByRoleId(Integer roleId) {
        return roleDeptDao.getDeptsByRoleId(roleId);
    }
}
