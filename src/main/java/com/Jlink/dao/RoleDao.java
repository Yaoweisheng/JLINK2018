package com.Jlink.dao;

import com.Jlink.entity.Role;

import java.util.List;

/**
 * @author binzhang
 * @date 2019-05-08
 */
public interface RoleDao {
    /**
     * 列出角色列表
     * @return areaList
     */
    List<Role> queryRole();

    Role queryRoleById(Integer roleId);

    int saveRole(Role role);
}
