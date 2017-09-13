package org.wxshan.springboot.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.wxshan.springboot.domain.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface PermissionMapper extends Mapper<Permission> {

    /**
     * 获取全部
     * @return
     */
    List<Permission> getAll();

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @Insert("insert into permission (category,permission_name,permission_display_name) values (#{category},#{permissionName},#{permissionDisplayName})")
    int add(Permission permission);

    /**
     * 根据Id查找权限
     * @param ids
     * @return
     */
    List<Permission> getByIds(Integer[] ids);
}
