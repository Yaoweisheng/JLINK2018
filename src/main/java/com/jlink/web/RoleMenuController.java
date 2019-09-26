package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.service.RoleMenuService;
import com.jlink.dto.RoleMenuObejct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("roleMenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 添加角色菜单
     * @param roleMenuObejct
     * @return
     */
    @RequestMapping(value = "addRoleMenu", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult addRoleMenu(@RequestBody RoleMenuObejct roleMenuObejct){
        try{
            roleMenuService.deleteByRoleId(roleMenuObejct.getRoleId());

            roleMenuService.batchSave(roleMenuObejct.getMenuIds(), roleMenuObejct.getRoleId());

            return ObjectResult.success(roleMenuService.getMenusByRoleId(roleMenuObejct.getRoleId()));
        }
        catch(Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    @RequestMapping(value = "getMenusByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getMenusByRoleId(@RequestParam Integer roleId){
        try{
            return ObjectResult.success(roleMenuService.getMenusByRoleId(roleId));
        }
        catch(Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
