package com.jlink.service.impl;

import com.jlink.dao.RoleDao;
import com.jlink.entity.Role;
import com.jlink.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author binzhang
 * @date 2019-05-08
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoleList() {
        return roleDao.queryRole();
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleDao.queryRoleById(roleId);
    }

    @Override
    public boolean saveRole(Role role) {
        return roleDao.saveRole(role) > 0;
    }
}
