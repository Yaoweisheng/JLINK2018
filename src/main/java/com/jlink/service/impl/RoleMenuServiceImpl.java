package com.jlink.service.impl;

import com.jlink.dao.RoleMenuDao;
import com.jlink.entity.RoleMenu;
import com.jlink.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public int save(RoleMenu roleMenu) {
        return roleMenuDao.save(roleMenu);
    }

    @Override
    public int batchSave(List<String> menuIds, Integer roleId) {
        return roleMenuDao.batchSave(menuIds, roleId);
    }

    @Override
    public int deleteByRoleId(Integer roleId) {
        return roleMenuDao.deleteByRoleId(roleId);
    }

    @Override
    public List<String> getMenusByRoleId(Integer roleId) {
        return roleMenuDao.getMenusByRoleId(roleId);
    }
}
