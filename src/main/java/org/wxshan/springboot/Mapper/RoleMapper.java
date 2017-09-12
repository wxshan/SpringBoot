package org.wxshan.springboot.Mapper;

import org.wxshan.springboot.domain.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface RoleMapper {
    int ifRoleNameExists(String roleName);

    List<Role> getAll();

    void delete(Integer id);

    void add(Role role);

    Role findById(Integer id);

    void update(Role role);
}
