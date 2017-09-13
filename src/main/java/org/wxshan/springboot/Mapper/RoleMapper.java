package org.wxshan.springboot.Mapper;

import org.wxshan.springboot.domain.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface RoleMapper {

    /**
     * 判断角色名是否存在
     * @param roleName
     * @return
     */
    int ifRoleNameExists(String roleName);

    /**
     * 获取全部
     * @return
     */
    List<Role> getAll();

    /**
     * 根据Id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加角色
     * @param role
     */
    void add(Role role);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 更新角色信息
     * @param role
     */
    void update(Role role);
}
