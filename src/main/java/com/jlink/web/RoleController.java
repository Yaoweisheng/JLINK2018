package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.entity.Role;
import com.jlink.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     * @return
     */
    @RequestMapping(value = "getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getRoleList(){
        try{
            return ObjectResult.success(roleService.getRoleList());
        }
        catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult addRole(@RequestBody Role role){
        try{
            if(StringUtils.isBlank(role.getName()) || StringUtils.isBlank(role.getCode())){
                return ObjectResult.error("必填项未填！");
            }
            Role roleExist = roleService.getRoleByName(role.getName());
            if (roleExist != null){
                return ObjectResult.error("角色已存在！");
            }
            role.setCreateTime(new Date());
            role.setIsActive(0);
            role.setIsDelete(0);
            boolean result = roleService.saveRole(role);
            if (result){
                Role roleData = roleService.getRoleById(role.getRoleId());
                return ObjectResult.success(roleData);
            }
            return ObjectResult.error("添加失败");

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "deleteRole", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResult deleteRole(@RequestParam Integer roleId){
        try{
            Role role = roleService.getRoleById(roleId);
            role.setIsDelete(1);
            if(roleService.updateRole(role)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("删除失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
