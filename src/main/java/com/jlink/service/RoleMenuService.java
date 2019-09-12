package com.jlink.service;

import com.jlink.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService {
    int save(RoleMenu roleMenu);

    int batchSave(List<String> menuIds, Integer roleId);

    int deleteByRoleId(Integer roleId);

    List<String> getMenusByRoleId(Integer roleId);
}
