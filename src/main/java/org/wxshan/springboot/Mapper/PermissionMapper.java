package org.wxshan.springboot.Mapper;

import org.wxshan.springboot.domain.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface PermissionMapper extends Mapper<Permission> {
    List<Permission> getAll();
}
