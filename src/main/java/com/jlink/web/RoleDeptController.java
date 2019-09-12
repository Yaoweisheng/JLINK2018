package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.service.RoleDeptService;
import com.jlink.service.RoleMenuService;
import com.jlink.vo.RoleDeptObejct;
import com.jlink.vo.RoleMenuObejct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("roleDept")
public class RoleDeptController {
    @Autowired
    private RoleDeptService roleDeptService;

    /**
     * 添加角色部门
     * @param roleDeptObejct
     * @return
     */
    @RequestMapping(value = "addRoleDept", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult addRoleMenu(@RequestBody RoleDeptObejct roleDeptObejct){
        try{
            roleDeptService.deleteByRoleId(roleDeptObejct.getRoleId());

            roleDeptService.batchSave(roleDeptObejct.getDeptNos(), roleDeptObejct.getRoleId());

            return ObjectResult.success(roleDeptService.getDeptsByRoleId(roleDeptObejct.getRoleId()));
        }
        catch(Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 根据角色获取部门
     * @param roleId
     * @return
     */
    @RequestMapping(value = "getDeptsByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getDeptsByRoleId(@RequestParam Integer roleId){
        try{
            return ObjectResult.success(roleDeptService.getDeptsByRoleId(roleId));
        }
        catch(Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
