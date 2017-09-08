package org.wxshan.springboot.user.service.Impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.wxshan.springboot.Mapper.UserMapper;
import org.wxshan.springboot.domain.User;
import org.wxshan.springboot.user.service.UserService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public boolean add(User user) {
        Integer ifUsernameExists = userMapper.ifUsernameExists(user.getUsername());
        if (ifUsernameExists.equals(0)){
            user.setCreateTime(LocalDateTime.now());
            user.setPwd(DigestUtils.md5Hex(user.getPwd()));
            userMapper.add(user);
            return true;
        }
        return false;
    }
}
