package org.wxshan.springboot.role.service;

import org.wxshan.springboot.domain.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface RoleService {

    /**
     * 获取分页角色信息
     * @param role
     * @return
     */
    List<Role> getPage(Role role);

    /**
     * 获取全部列表
     * @return
     */
    List<Role> getAll();

    /**
     * 根据id删除角色
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean add(Role role);

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 判断角色名是否存在
     * @param roleName
     * @return
     */
    int ifRoleNameExists(String roleName);

    /**
     * 更新角色
     * @param role
     */
    void update(Role role);
}
