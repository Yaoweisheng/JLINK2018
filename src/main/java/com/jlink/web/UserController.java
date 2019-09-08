package com.jlink.web;

import com.jlink.util.SmsUtil;
import com.jlink.vo.LoginObject;
import com.jlink.vo.UserChangePasswordObject;
import com.jlink.dto.ObjectResult;
import com.jlink.entity.Role;
import com.jlink.entity.User;
import com.jlink.service.RoleService;
import com.jlink.service.TokenService;
import com.jlink.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author binzhang
 * @date 2019-05-06
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取用户类型
     * @return
     */
    @RequestMapping(value = "getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getRoleList(){
        List<Role> roleListData = roleService.getRoleList();
        List<Role> roleList = new LinkedList<>();
        for (Role r :
                roleListData) {
            if (r.getRoleName().equals("企业用户") || r.getRoleName().equals("咨询机构")){
                roleList.add(r);
            }
        }
        return ObjectResult.success(roleList);
    }
    
    /**
     * 获取更多用户类型
     * @return
     */
    @RequestMapping(value = "getMoreRolelist", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getMoreRolelist(){
        List<Role> roleListData = roleService.getRoleList();
        List<Role> roleList = new LinkedList<>();
        for (Role r :
                roleListData) {
            if (!r.getRoleName().equals("系统管理员")){
                roleList.add(r);
            }
        }
        return ObjectResult.success(roleList);
    }

    /**
     * 根据id获取角色
     * @param roleId
     * @return
     */
    @RequestMapping(value = "getrolebyid", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getRoleById(@RequestParam Integer roleId){
        try{
            Role role = roleService.getRoleById(roleId);
            return ObjectResult.success(role);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 测试发送短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sendmessage", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult sendMessage(@RequestParam String phone){
        try{
            boolean result = SmsUtil.sendMessage(phone);
            return ObjectResult.success(result);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 测试验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "validmessage", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult validMessage(@RequestParam String phone, @RequestParam String code){
        try{
            boolean result = SmsUtil.validMessage(phone, code);
            return ObjectResult.success(result);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "saverole", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult saveRole(@RequestBody Role role){
        try{
            if(StringUtils.isBlank(role.getRoleCode()) || StringUtils.isBlank(role.getRoleName())){
                return ObjectResult.error("必填项未填！");
            }
            boolean result = roleService.saveRole(role);
            if (result){
                return ObjectResult.success(role);
            }
            return ObjectResult.error("添加失败");

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    @RequestMapping(value="getuserlist", method= RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String user = request.getParameter("user");
        try {

            modelMap.put("user", user);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult register(@RequestBody User user){
        try{
            if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getEmail()) || user.getRoleId() == null){
                return ObjectResult.error("必填项未填！");
            }
            User userExist = userService.getUserByUsername(user.getUsername());
            if (userExist != null){
                return ObjectResult.error("用户已存在！");
            }
//            user.setPassword(DESUtils.getEncryptString(user.getPassword()));
//            System.out.println("Date:"+new Date());
            user.setCreateTime(new Date());
            user.setPhone(user.getUsername());
            user.setStatus(0);
            boolean result = userService.saveUser(user);
            if (result){
                User userData = userService.getUserById(user.getUserId());
                return ObjectResult.success(userData);
            }
            return ObjectResult.error("添加失败");

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult login(@RequestBody User user){
        try{
            if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
                return ObjectResult.error("必填项未填！");
            }
            User userData = userService.getUserByUsername(user.getUsername());
            if (userData == null){
                return ObjectResult.error("用户不存在！");
            }
            if (!userData.getPassword().equals(user.getPassword())){
                return ObjectResult.error("密码错误");
            }
            String token = tokenService.getToken(userData);
            LoginObject loginObject = new LoginObject(userData, token);
            return ObjectResult.success(loginObject);

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "getVerificationCode", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult getVerificationCode(@RequestParam String phone){
        try{
            boolean result = SmsUtil.sendMessage(phone);
            return ObjectResult.success(result);
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }
    /**
     * 修改绑定手机
     * @param phone
     * @return
     */
    @RequestMapping(value = "modifyPhone", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult modifyPhone(@RequestParam String phone, @RequestParam String code){
        try{
            boolean result = SmsUtil.validMessage(phone, code);
            if(result){
                return ObjectResult.success(result);
            }
            else{
                return ObjectResult.error("验证码错误");
            }
        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }


    /**
     * 用户个人信息修改
     * @param user
     * @return
     */
    @RequestMapping(value = "personalInformationModification", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult personalInformationModification(@RequestBody User user){
        try{
            if(StringUtils.isBlank(user.getUsername())){
                return ObjectResult.error("未指定用户");
            }
            if(StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getAddress()) || StringUtils.isBlank(user.getEmail())){
                return ObjectResult.error("必填项未填！");
            }
            User userData = userService.getUserByUsername(user.getUsername());
            userData.setName(user.getName());
            userData.setAddress(user.getAddress());
            userData.setEmail(user.getEmail());
            boolean result = userService.updateUser(userData);
            if (result){
                return ObjectResult.success(userData);
            }
            return ObjectResult.error("添加失败");

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }


    /**
     * 修改密码,输入旧密码
     * @param user
     * @return
     */
    @RequestMapping(value = "oldPassword", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult changePassword1(@RequestBody User user){
        try{
            if(user.getUserId() == null || StringUtils.isBlank(user.getPassword())){
                return ObjectResult.error("必填项未填！");
            }
            User userData = userService.getUserById(user.getUserId());
            if (userData == null){
                return ObjectResult.error("用户不存在！");
            }
            if (!userData.getPassword().equals(user.getPassword())){
                return ObjectResult.error("密码错误");
            }
            return ObjectResult.success(userData);

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

    /**
     * 修改密码
     * @param object
     * @return
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult changePassword(@RequestBody UserChangePasswordObject object){
        try{
            if(object.getUserId() == null || StringUtils.isBlank(object.getPassword()) || StringUtils.isBlank(object.getNewPassword())){
                return ObjectResult.error("必填项未填！");
            }
            User userData = userService.getUserById(object.getUserId());
            if (userData == null){
                return ObjectResult.error("用户不存在！");
            }
            if (!userData.getPassword().equals(object.getPassword())){
                return ObjectResult.error("密码错误");
            }
            userData.setPassword(object.getNewPassword());
            userService.updateUser(userData);
            return ObjectResult.success(userData);

        }catch (Exception e){
            return ObjectResult.error(e.getMessage());
        }
    }

}
