package com.jlink.dao;

import com.jlink.entity.Role;

import java.util.List;

/**
 * @author yaoweisheng
 * @date 2019-09-10
 */
public interface RoleDao {
    List<Role> queryRole();

    Role queryRoleById(Integer roleId);

    Role queryRoleByName(String name);

    int saveRole(Role role);

    int updateRole(Role role);
}
