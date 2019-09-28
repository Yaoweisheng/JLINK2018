package com.jlink.web;

import com.jlink.dto.HolidayObject;
import com.jlink.dto.ObjectResult;
import com.jlink.dto.PagingObject;
import com.jlink.entity.Holiday;
import com.jlink.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("holiday")
public class HolidayController {
    @Autowired
    private HolidayService holidayService;
    @RequestMapping(value = "batchSave", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult batchSave(@RequestBody HolidayObject holidayObject){
        try{
            if(holidayService.batchSave(holidayObject)){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult query(@RequestParam Integer festival, @RequestParam Integer page, @RequestParam Integer per){
        try{
            List<Holiday> list = holidayService.query(festival, page, per);
            int count = holidayService.count(festival);
            return ObjectResult.success(new PagingObject<Holiday>(list, count));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult batchDelete(@RequestBody HolidayObject holidayObject){
        try{
            if(holidayService.batchDelete(holidayObject.getIds())){
                return ObjectResult.success(null);
            }
            return ObjectResult.error("删除失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
