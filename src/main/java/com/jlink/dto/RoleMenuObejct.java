package com.jlink.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuObejct {
    private List<String> menuIds;
    private int roleId;
}
