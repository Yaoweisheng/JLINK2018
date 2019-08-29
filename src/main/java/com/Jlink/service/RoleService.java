package com.Jlink.service;

import com.Jlink.entity.Role;

import java.util.List;

/**
 * @author binzhang
 * @date 2019-05-08
 */
public interface RoleService {
    List<Role> getRoleList();

    Role getRoleById(Integer roleId);

    boolean saveRole(Role role);
}
