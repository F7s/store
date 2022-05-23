package com.cy.store.service;

import com.cy.store.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author lizhenghao
 * @create 2022-02-16-19:47
 */
public interface IUserService {
    Integer reg(User user);
    User login(String username,String password);
    Integer updatePassword(Integer uid,String oldPassword, String newPassword);
    Integer changeInfo(Integer uid,String username,User user);
    User getByUid(Integer uid);

    /**
     * 修改用户头像
     * @param uid 用户的id
     * @param avatar 头像路径
     * @param username 用户名
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
