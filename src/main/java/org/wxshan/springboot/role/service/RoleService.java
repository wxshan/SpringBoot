package org.wxshan.springboot.role.service;

import org.wxshan.springboot.domain.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface RoleService {
    
    List<Role> getPage(Role role);

    List<Role> getAll();

    void delete(Integer id);

    boolean add(Role role);

    Role findById(Integer id);

    int ifRoleNameExists(String roleName);

    void update(Role role);
}
