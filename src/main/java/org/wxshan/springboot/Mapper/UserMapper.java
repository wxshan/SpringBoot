package org.wxshan.springboot.Mapper;

import org.apache.ibatis.annotations.Select;
import org.wxshan.springboot.domain.User;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @Select("select count(*) from manager where username = #{username} ")
    Integer ifUsernameExists(String username);

    /**
     * 根据ID删除用户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据ID获取用户名
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

}
