package com.jlink.dao;

import com.jlink.entity.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getRoots();
    List<Menu> getChildren(String parentId);
}