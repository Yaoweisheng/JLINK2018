package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("area")
public class AreaController {
    @Autowired
    AreaService areaService;
    /**
     * 查询省
     */
    @RequestMapping(value = "getProvinces", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getProvinces(){
        try{
            return ObjectResult.success(areaService.getProvinces());
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 根据省查询市
     */

    @RequestMapping(value = "getCitys", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getProvinces(@RequestParam String provinceCode){
        try{
            return ObjectResult.success(areaService.getCitys(provinceCode));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 根据市查询区
     */

    @RequestMapping(value = "getAreas", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getAreas(@RequestParam String cityCode){
        try{
            return ObjectResult.success(areaService.getAreas(cityCode));
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
