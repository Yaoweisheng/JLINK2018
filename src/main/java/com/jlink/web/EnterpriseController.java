package com.jlink.web;

import com.jlink.dto.ObjectResult;
import com.jlink.entity.Enterprise;
import com.jlink.service.EnterpriseService;
import com.jlink.service.RoleService;
import com.jlink.service.UserService;
import com.jlink.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("enterprise")
public class EnterpriseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 企业信息完善
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "addEnterprise", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult informationPerfection(@RequestBody Enterprise enterprise){
        try{
            if(StringUtils.isBlank(enterprise.getEnterpriseName()) || StringUtils.isBlank(enterprise.getLegelPerson()) || StringUtils.isBlank(enterprise.getEnterpriseType()) || StringUtils.isBlank(enterprise.getEnterpriseCode()) || enterprise.getStartTime() == null ||  StringUtils.isBlank(enterprise.getLicenceImg()) || enterprise.getUserId()==null){
                return ObjectResult.error("必填项未填！");
            }
            enterprise.setCreateTime(new Date());
            boolean result = enterpriseService.saveEnterprise(enterprise);
            if(result){
                return ObjectResult.success(enterpriseService.getEnterpriseById(enterprise.getEnterpriseId()));
            }
            return ObjectResult.error("添加失败");
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "imageUpload",method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult fileUpload(@RequestParam("file") MultipartFile file)  {
        String fileName = FileUtil.getRandomFileName()+file.getOriginalFilename();
        String path = FileUtil.getImgBasePath();
        try {
            file.transferTo(new File(path, fileName));
            return ObjectResult.success(FileUtil.getImgRelativePath()+fileName);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
}
