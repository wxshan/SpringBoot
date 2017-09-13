package org.wxshan.springboot.role.service.Impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxshan.springboot.Mapper.RoleMapper;
import org.wxshan.springboot.domain.Role;
import org.wxshan.springboot.role.service.RoleService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getPage(Role role) {
        if (role.getPageNo() != null && role.getPageSize() != null) {
            PageHelper.startPage(role.getPageNo(), role.getPageSize());
        }
        return roleMapper.getAll();
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.getAll();
    }

    @Override
    public void delete(Integer id) {
        roleMapper.delete(id);
    }

    @Override
    public boolean add(Role role) {
        int ifRoleNameExists = roleMapper.ifRoleNameExists(role.getRoleName());
        if (ifRoleNameExists == 0) {
            role.setCreateTime(LocalDateTime.now());
            role.setUpdateTime(LocalDateTime.now());
            roleMapper.add(role);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public int ifRoleNameExists(String roleName) {
        return roleMapper.ifRoleNameExists(roleName);
    }

    @Override
    public void update(Role role) {
        roleMapper.update(role);
    }
}
