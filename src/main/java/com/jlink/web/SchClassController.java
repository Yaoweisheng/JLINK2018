package com.jlink.web;

import com.beust.jcommander.Parameter;
import com.jlink.dto.ObjectResult;
import com.jlink.dto.PagingObject;
import com.jlink.entity.SchClass;
import com.jlink.service.SchClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("schClass")
public class SchClassController {
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
            List<SchClass> list = schClassService.query(page, per);
            int count = schClassService.count();
            return ObjectResult.success(new PagingObject<SchClass>(list, count));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 新增
     * @param schClass
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult query(@RequestBody SchClass schClass){
        try{
            if(schClassService.save(schClass)){
                return ObjectResult.success(schClass);
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResult delete(@RequestParam Integer id){
        try{
            if(schClassService.delete(id)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("删除失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 更新
     * @param schClass
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResult update(@RequestBody SchClass schClass){
        try{
            if(schClassService.update(schClass)){
                return ObjectResult.success(schClass);
            }
            return ObjectResult.error("更新失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
