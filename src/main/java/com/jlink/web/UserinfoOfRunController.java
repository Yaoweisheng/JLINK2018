package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.dto.PagingObject;
import com.jlink.dto.UserinfoOfRunBatchObject;
import com.jlink.dto.UserinfoOfRunObject;
import com.jlink.entity.Department;
import com.jlink.entity.Userinfo;
import com.jlink.entity.UserinfoOfRun;
import com.jlink.service.DepartmentService;
import com.jlink.service.UserinfoOfRunService;
import com.jlink.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("userinfoOfRun")
public class UserinfoOfRunController {
    @Autowired
    private UserinfoOfRunService userinfoOfRunService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult query(@RequestParam Integer numRunId, @RequestParam String deptNo, @RequestParam String name,
                              @RequestParam Integer page, @RequestParam Integer per){
        try{
            List<UserinfoOfRun> list = userinfoOfRunService.query(numRunId, deptNo, name, page, per);
            List<UserinfoOfRunObject> userinfoOfRunObjectList = new LinkedList<>();
//            list.forEach(n->userinfoOfRunObjectList.append(new UserinfoOfRunObject(n.getNumRunId());
            for(UserinfoOfRun u :list){
                UserinfoOfRunObject userinfoOfRunObject = new UserinfoOfRunObject();
                userinfoOfRunObject.setUserinfoId(u.getUserinfoId());
                Userinfo userinfo = userinfoService.getUserinfoByUserId(u.getUserinfoId());
                userinfoOfRunObject.setBadgenumber(userinfo.getBadgenumber());
                String tDeptNo = userinfo.getDeptNo();
                Department department = departmentService.getDepartmentByNo(tDeptNo);
                if (department != null){
                    userinfoOfRunObject.setDepartmentName(department.getDeptName());
                }
                userinfoOfRunObject.setStartDate(u.getStartdate());
                userinfoOfRunObject.setEndDate(u.getEnddate());
                userinfoOfRunObject.setName(userinfo.getName());
                userinfoOfRunObjectList.add(userinfoOfRunObject);
            }
            int count = userinfoOfRunService.count(numRunId, deptNo, name);
            return ObjectResult.success(new PagingObject<UserinfoOfRunObject>(userinfoOfRunObjectList, count));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 添加
     * @param userinfoOfRunBatchObject
     * @return
     */
    @RequestMapping(value = "batchSave", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult batchSave(@RequestBody UserinfoOfRunBatchObject userinfoOfRunBatchObject){
        try{
            for (UserinfoOfRun u :
                 userinfoOfRunBatchObject.list) {
                List<UserinfoOfRun> list = userinfoOfRunService.getByUserinfoId(u.getUserinfoId());
                if(list.size()==0){
                    continue;
                }
                for (UserinfoOfRun u1 :
                        list) {
                    if(!(u1.getStartdate().compareTo(u.getEnddate()) > 0 || u1.getEnddate().compareTo(u.getEnddate()) < 0)){
                        String name = userinfoService.getUserinfoByUserId(u.getUserinfoId()).getName();
                        return ObjectResult.error(name+"排班时间冲突");
                    }
                }
            }

            if(userinfoOfRunService.batchSave(userinfoOfRunBatchObject.list)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 删除
     * @param userinfoOfRunBatchObject
     * @return
     */
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult batchDelete(@RequestBody UserinfoOfRunBatchObject userinfoOfRunBatchObject){
        try{
            if(userinfoOfRunService.batchDelete(userinfoOfRunBatchObject.list)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
