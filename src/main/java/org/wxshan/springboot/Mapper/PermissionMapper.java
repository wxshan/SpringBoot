package org.wxshan.springboot.Mapper;

import org.wxshan.springboot.domain.Permission;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface PermissionMapper {
    List<Permission> getAll();
}
