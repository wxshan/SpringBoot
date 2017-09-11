package org.wxshan.springboot.permission.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wxshan.springboot.base.BaseController;
import org.wxshan.springboot.domain.Permission;
import org.wxshan.springboot.permission.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @Resource
    private PermissionService permissionServiceImpl;

    @RequestMapping(value = "getALL",method = RequestMethod.POST)
    public Map<String, Object> getAll(){

        List<Permission> all = permissionServiceImpl.getAll();

        return generateSuccessResult(all);
    }

}
