package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author lizhenghao
 * @create 2022-02-15-15:26
 */
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的的数据
     * @return 受影响的行数（增删改都会有，判断是否成功）
     */
    Integer insert(User user);

    /**
     *  根据用户名查询用户数据
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);
    User findByUid(Integer uid);

    /**
     * 根据uid来修改密码
     * @param
     * @return
     */
    Integer updatePasswordByUid(User user);

    Integer updateInfoByUid(User user);

    /**
     * 根据用户的uid值来修改用户的头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(Integer uid,
                              String avatar,
                              String modifiedUser,
                              Date modifiedTime);
}
