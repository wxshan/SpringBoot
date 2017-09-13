package org.wxshan.springboot.permission.service;

import org.wxshan.springboot.domain.Permission;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public interface PermissionService {

    /**
     * 获取全部权限
     * @return
     */
    List<Permission> getAll();

    /**
     * 根据Id获取权限
     * @param permissionIDs
     * @return
     */
    List<Permission> getByIds(Integer[] permissionIDs);
}
