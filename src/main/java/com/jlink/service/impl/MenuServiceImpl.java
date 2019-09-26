package com.jlink.service.impl;

import com.jlink.dao.MenuDao;
import com.jlink.entity.Menu;
import com.jlink.service.MenuService;
import com.jlink.dto.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Node> getTree() {
        List<Node> nodes = new LinkedList<>();
        List<Menu> menus = menuDao.getRoots();
        for (Menu m :
                menus) {
            Node node = new Node();
            node.setId(m.getMenuId());
            node.setText(m.getName());
            node.setChildren(getChildren(m.getMenuId()));
            nodes.add(node);
        }
        return nodes;
    }

    private List<Node> getChildren(String parentId){
        List<Node> nodes = new LinkedList<>();
        List<Menu> menus = menuDao.getChildren(parentId);
        for (Menu m:
             menus) {
            Node node = new Node();
            node.setId(m.getMenuId());
            node.setText(m.getName());
            node.setChildren(getChildren(m.getMenuId()));
            nodes.add(node);
        }
        return nodes;
    }
}
