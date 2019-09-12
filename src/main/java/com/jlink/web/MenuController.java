package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.service.MenuService;
import com.jlink.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "getMenuTree", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getMenuTree(){
        try{
            List<Node> nodes = menuService.getTree();
            return ObjectResult.success(nodes);
        }
        catch(Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
