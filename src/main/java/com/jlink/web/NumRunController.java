package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.dto.PagingObject;
import com.jlink.entity.NumRun;
import com.jlink.entity.NumRunDeil;
import com.jlink.entity.SchClass;
import com.jlink.service.NumRunDeilService;
import com.jlink.service.NumRunService;
import com.jlink.service.SchClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("numRun")
public class NumRunController {
    @Autowired
    private NumRunService numRunService;
    @Autowired
    private NumRunDeilService numRunDeilService;
    @Autowired
    private SchClassService schClassService;
    /**
     * 查询
     * @param page
     * @param per
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult query(@RequestParam Integer page, @RequestParam Integer per){
        try{
            List<NumRun> list = numRunService.query(page, per);
            int count = numRunService.count();
            return ObjectResult.success(new PagingObject<NumRun>(list, count));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResult batchDelete(@RequestParam Integer id){
        try{
            if(numRunService.delete(id)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("删除失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult save(@RequestBody NumRun numRun){
        try{
            if(numRunService.save(numRun)){
                return ObjectResult.success(numRun);
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResult update(@RequestBody NumRun numRun){
        try{
            if(numRunService.update(numRun)){
                return ObjectResult.success(numRun);
            }
            return ObjectResult.error("更新失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 添加班次排班时段
     * @return
     */
    @RequestMapping(value = "saveDeil", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult saveDeil(@RequestBody NumRunDeil numRunDeil){
        try{
            SchClass schClass = schClassService.getById(numRunDeil.getSchclassid());
            numRunDeil.setStarttime(schClass.getStartTime());
            numRunDeil.setEndtime(schClass.getEndTime());
            numRunDeil.setOvertime(0);
            if(numRunDeilService.save(numRunDeil)){
                return ObjectResult.success(numRunDeil);
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 查询班次排班时段
     * @return
     */
    @RequestMapping(value = "queryDeils", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult queryDeils(@RequestParam Integer id, @RequestParam Integer page, @RequestParam Integer per){
        try{
            List<NumRunDeil> list = numRunDeilService.query(id, page, per);
            int count = numRunDeilService.count(id);
            return ObjectResult.success(new PagingObject<NumRunDeil>(list, count));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 查询班次排班时段
     * @return
     */
    @RequestMapping(value = "deleteDeil", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult deleteDeil(@RequestBody NumRunDeil numRunDeil){
        try{
            numRunDeil.setStarttime(schClassService.getById(numRunDeil.getSchclassid()).getStartTime());
            if(numRunDeilService.delete(numRunDeil)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("删除失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
