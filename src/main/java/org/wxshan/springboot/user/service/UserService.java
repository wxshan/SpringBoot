package org.wxshan.springboot.user.service;

import org.wxshan.springboot.domain.User;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
public interface UserService {

    User getByUsername(String username);

    boolean add(User user);
}