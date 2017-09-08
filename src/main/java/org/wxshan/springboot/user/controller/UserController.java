package org.wxshan.springboot.user.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wxshan.springboot.base.BaseController;
import org.wxshan.springboot.domain.Permission;
import org.wxshan.springboot.domain.User;
import org.wxshan.springboot.user.service.UserService;
import org.wxshan.springboot.utils.serializer.GroupUpdateForPwd;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by Administrator on 2017/9/8 0008.
 */
@Log4j2
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String, Object> login(User user){
       Subject subject = SecurityUtils.getSubject();
       subject.login(new UsernamePasswordToken(user.getUsername(), user.getPwd()));
        Session session = subject.getSession();
        User user1 = userService.getByUsername(user.getUsername());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("userId",user1.getId());
        session.setAttribute("role",user1.getRole().getRoleName());

        List<String> strings = user1.getRole().getPermissionList().stream().map(Permission::getPermissionName).collect(Collectors.toList());
        session.setAttribute("permission", strings);

        return generateSuccessResult(user);
    }

    /**
     * 添加用户
     * @param user
     * @param bindingResult
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //@RequiresPermissions("user:read")
    public  Map<String, Object> add(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return generateValidErrorInfo(bindingResult);
        }

        boolean add = userService.add(user);
        if (add) {
            return generateSuccessResult();
        }else {
            return generateFailureResult(1003,"username exists");
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Map<String, Object> delete(Integer id){

        userService.deleteById(id);
        return generateSuccessResult();
    }

    @RequiresAuthentication
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @RequiresPermissions("user:read")
    public Map<String, Object> update(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return generateValidErrorInfo(bindingResult);
        }

        User user1 = userService.getById(user.getId());
        if (user1 != null) {
            if (!user.getUsername().equals(user1.getUsername())){
                Integer ifUserNameExists = userService.ifUsernameExists(user.getUsername());
                if (!ifUserNameExists.equals(0)){
                    return generateFailureResult(1003,"username exists");
                }
            }
            userService.update(user);
            return generateSuccessResult();

        }else {
            return generateFailureResult(1000,"this data not exists");
        }
    }

    /**
     * 修改密码
     * @param user
     * @param bindingResult
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Map<String,Object> changePassword(@Validated(GroupUpdateForPwd.class) User user,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return generateValidErrorInfo(bindingResult);
        }
        User user1 = userService.getById(user.getId());
        if (user1 == null) {
            return generateFailureResult(1001,"account not exists");
        }

        user.setPwd(DigestUtils.md5Hex(user.getNewpassword()));
        userService.update(user);
        return generateSuccessResult();
    }

    /**
     * 获取单个用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSingleUser")
    @RequiresAuthentication
    @RequiresPermissions("user:read")
    public Map<String, Object> getSingleUser(Integer id){
        User user = userService.getById(id);
        if (user == null) {
            return generateFailureResult(1001,"account not exists");
        }

        return generateSuccessResult();
    }

    /**
     * 用户注销
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value = "/logout")
    public Map<String, Object> logout(){

        SecurityUtils.getSubject().logout();
        return generateSuccessResult();
    }

    @ModelAttribute
    public void getById(@RequestParam(value = "id", required = false) Integer id,Map<String,Object> map){
        if (id != null){
            User user = userService.getById(id);
            if (user != null){
                map.put("user",user);
            }
        }
    }
}
