package org.wxshan.springboot.permission.service;

import org.wxshan.springboot.domain.Permission;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface PermissionService {

    List<Permission> getAll();

    List<Permission> getByIds(Integer[] permissionIDs);
}
