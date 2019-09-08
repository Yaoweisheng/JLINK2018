package com.jlink.web;

import com.auth0.jwt.JWT;
import com.jlink.dto.ObjectResult;
import com.jlink.entity.Department;
import com.jlink.entity.User;
import com.jlink.service.DepartmentService;
import com.jlink.service.UserService;
import com.jlink.vo.DepartmentObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;
    /**
     * 获取所有部门
     * @return
     */
    @RequestMapping(value = "getDepartmentList", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getDepartmentList(){
        List<Department> departmentList = departmentService.getDepartmentList();
        return ObjectResult.success(departmentList);
    }


    /**
     * 添加部门
     * @param departmentObject
     * @return
     */
    @RequestMapping(value = "addDepartment", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult addDepartment(@RequestBody DepartmentObject departmentObject, HttpServletRequest httpServletRequest){
        try{
            if(StringUtils.isBlank(departmentObject.getDeptName())){
                return ObjectResult.error("必填项未填！");
            }
            String deptNo = departmentService.getNewNo(departmentObject.getDeptParentNo());
            if(deptNo.equals("-1")){
                return ObjectResult.error("添加失败");
            }
            Department department = new Department(deptNo, departmentObject.getDeptName(), departmentObject.getDeptAddr());
            String token = httpServletRequest.getHeader("token");
            int userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
            //User user = userService.getUserById(userId);
            department.setCreateId(userId);
            department.setCreateDate(new Date());
            boolean result = departmentService.saveDepartment(department);
            if(result){
                return ObjectResult.success(departmentService.getDepartmentById(department.getDeptId()));
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 更新部门
     * @param department
     * @return
     */
    @RequestMapping(value = "updateDepartment", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResult updateDepartment(@RequestBody Department department, HttpServletRequest httpServletRequest) {
        try {
            Department departmentData = departmentService.getDepartmentById(department.getDeptId());
            departmentData.setDeptName(department.getDeptName());
            departmentData.setModifyDate(new Date());
            departmentData.setDeptAddr(department.getDeptAddr());
            String token = httpServletRequest.getHeader("token");
            int userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
            departmentData.setModifyId(userId);
            boolean result = departmentService.updateDepartment(departmentData);
            if (result) {
                return ObjectResult.success(departmentService.getDepartmentById(department.getDeptId()));
            }
            return ObjectResult.error("更新失败");
        } catch (Exception e) {
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 获取所有部门
     * @return
     */
    @RequestMapping(value = "getPlinenubs", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getPlinenubs(){
        List<String> plinenubs = departmentService.getPlinenubs();
        return ObjectResult.success(plinenubs);
    }

}
