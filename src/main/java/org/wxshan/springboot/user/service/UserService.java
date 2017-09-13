package org.wxshan.springboot.user.service;

import org.wxshan.springboot.domain.User;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface UserService {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 根据ID删除用户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    Integer ifUsernameExists(String username);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);
}
