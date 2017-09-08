package org.wxshan.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxshan.springboot.domain.User;
import org.wxshan.springboot.user.service.UserService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by Administrator on 2017/9/8 0008.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    private UserService userService;

    @Test
    public void add() {
        User user = new User();
        for (int i = 0; i <20 ; i++) {

            user.setUsername("admin"+i);
            user.setPwd(DigestUtils.md5Hex("123456"));
            user.setCreateTime(LocalDateTime.now());
            user.setName("Linzai"+i);
            user.setRoleId(4);
            userService.add(user);
        }
    }
}
