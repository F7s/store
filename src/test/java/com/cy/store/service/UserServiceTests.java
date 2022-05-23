package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lizhenghao
 * @create 2022-02-16-20:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        User user = new User();
        user.setUsername("lizhengz");
        user.setPassword("123");
        Integer rows = userService.reg(user);
        System.out.println(rows);
    }

    @Test
    public void login(){
        User abc = userService.login("admin", "as");
        System.out.println(abc);
    }

    @Test
    public void updatePassword(){
        userService.updatePassword(10, "as", "admin");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(10));
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setGender(0);
        user.setPhone("110");
        user.setEmail("110@email.com");
        userService.changeInfo(10, "admin",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(13, "/abc","管梨园");
    }
}
