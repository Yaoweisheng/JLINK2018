package com.jlink.web;

import com.jlink.dto.HolidayObject;
import com.jlink.dto.ObjectResult;
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
            holidayService.batchSave(holidayObject);
            return ObjectResult.success(null);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult query(@RequestParam Integer festival, @RequestParam Integer page, @RequestParam Integer per){
        try{
            List<Holiday> list = holidayService.query(festival, page, per);
            return ObjectResult.success(list);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult batchDelete(@RequestBody HolidayObject holidayObject){
        try{
            holidayService.batchDelete(holidayObject.getIds());
            return ObjectResult.success(null);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
