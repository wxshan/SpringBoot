package org.wxshan.springboot.role.controller;

import com.github.pagehelper.Page;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wxshan.springboot.base.BaseController;
import org.wxshan.springboot.domain.Permission;
import org.wxshan.springboot.domain.Role;
import org.wxshan.springboot.permission.service.PermissionService;
import org.wxshan.springboot.role.service.RoleService;
import org.wxshan.springboot.utils.serializer.GroupAdd;
import org.wxshan.springboot.utils.serializer.GroupUpdate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{

    @Resource
    private RoleService roleServiceImpl;
    @Resource
    private PermissionService permissionService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresPermissions("role:read")
    public Map<String, Object> list(Role role) {

        Map<String, Object> map = new HashedMap();
        List<Role> roles = roleServiceImpl.getPage(role);
        map.put("list", roles);
        map.put("totalCount",((Page)roles).getTotal());
        map.put("totalPage",((Page)roles).getPages());
        return  generateSuccessResult(map);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresPermissions(value = {"user:read","role:read"}, logical = Logical.OR)
    public Map<String, Object> getAll(){
        List<Role> all = roleServiceImpl.getAll();
        return  generateSuccessResult(all);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresPermissions("role:read")
    public Map<String, Object> delete(Integer id){
        roleServiceImpl.delete(id);
        return generateSuccessResult();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresPermissions("role:read")
    public Map<String, Object> add(@Validated(GroupAdd.class) Role role, Integer[] permissionIDs){

        List<Permission> permissionList = permissionService.getByIds(permissionIDs);
        role.setPermissionList(permissionList);
        boolean add = roleServiceImpl.add(role);
        if (add){
            return  generateSuccessResult();
        } else {
            return generateFailureResult(2000,"roleName exists");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresPermissions("role:read")
    public Map<String, Object> update(@Validated({GroupUpdate.class}) Role role, Integer[] permissionIds){

        Role role1 = roleServiceImpl.findById(role.getId());
        if (role1 == null){
            return generateFailureResult(1000,"this data not exists");
        }

        if (!role.getRoleName().equals(role1.getRoleName())){
            int ifRoleNameExists = roleServiceImpl.ifRoleNameExists(role.getRoleName());
            if (ifRoleNameExists > 0){
                return generateFailureResult(2000,"roleName exists");
            }
        }
        List<Permission> permissionList = permissionService.getByIds(permissionIds);
        role.setPermissionList(permissionList);
        role.setUpdateTime(LocalDateTime.now());
        roleServiceImpl.update(role);
        return generateSuccessResult();
    }

    @RequestMapping(value = "/getSingleRole", method = RequestMethod.POST)
    @RequiresAuthentication
    @RequiresPermissions("role:read")
    public  Map<String, Object> getSingleRole(Integer id){
        Role role = roleServiceImpl.findById(id);
        if (role == null) {
            return  generateFailureResult(1000,"this data not exists");
        }
        return generateSuccessResult(role);
    }
}
