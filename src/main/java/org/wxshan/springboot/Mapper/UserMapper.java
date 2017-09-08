package org.wxshan.springboot.Mapper;

import org.apache.ibatis.annotations.Select;
import org.wxshan.springboot.domain.User;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface UserMapper {

    User getByUsername(String username);

    int add(User user);

    @Select("select count(*) from manager where username = #{username} ")
    Integer ifUsernameExists(String username);

    void deleteById(Integer id);

    User getById(Integer id);

    void update(User user);

}
