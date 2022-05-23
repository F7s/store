package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author lizhenghao
 * @create 2022-02-16-19:50
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Integer reg(User user) {
        String username = user.getUsername();

        User result = userMapper.findByUsername(username);

        if (result != null){
            throw new UsernameDuplicatedException("用户名已存在");
        }

        String oldPaddword = user.getPassword();
        //获取盐值（随机生成）
        String salt = UUID.randomUUID().toString().toUpperCase();

        String md5Password = getMD5Password(oldPaddword, salt);

        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("产生了未知异常");
        }


        return rows;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null ||user.getIsDelete() == 1){
            throw new UserNotFoundException("用户名未找到");
        }
        String salt = user.getSalt();
        String md5Password = getMD5Password(password, salt);
        if ( !md5Password.equals(user.getPassword())){
            throw new PasswordNotMatchException("用户名密码错误");
        }

        User newUser = new User();
        newUser.setUid(user.getUid());
        newUser.setUsername(user.getUsername());
        newUser.setAvatar(user.getAvatar());

        return newUser;
    }

    @Override
    public Integer updatePassword(Integer uid,String oldPassword,String newPassword) {
        User result = userMapper.findByUid(uid);
        String salt = result.getSalt();
        String md5Password = getMD5Password(oldPassword, salt);
        if ( !md5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("原密码错误");
        }
        String newMd5Password = getMD5Password(newPassword, salt);
        User user = new User();
        user.setUid(uid);
        user.setPassword(newMd5Password);
        user.setModifiedUser(result.getUsername());
        Date date = new Date();
        user.setModifiedTime(date);
        Integer row = userMapper.updatePasswordByUid(user);
        if (row != 1){
            throw new UpdateException("修改密码时产生未知异常");
        }
        return row;

    }

    @Override
    public Integer changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1){
            throw new UpdateException("更新数据时产生了未知的异常");
        }
        return rows;

    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return user;

    }

    /**
     * 修改用户头像
     *
     * @param uid      用户的id
     * @param avatar   头像路径
     * @param username 用户名
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新用户头像时产生异常");
        }
    }

    private String getMD5Password(String password, String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
