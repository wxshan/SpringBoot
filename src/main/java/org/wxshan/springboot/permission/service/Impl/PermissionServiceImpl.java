package org.wxshan.springboot.permission.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxshan.springboot.Mapper.PermissionMapper;
import org.wxshan.springboot.domain.Permission;
import org.wxshan.springboot.permission.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

@Transactional
@Service
public class PermissionServiceImpl implements PermissionService{

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getAll() {
        return permissionMapper.getAll();
    }

    @Override
    public List<Permission> getByIds(Integer[] permissionIDs) {
        return (List<Permission>) permissionMapper.selectByPrimaryKey(permissionIDs);
    }
}
