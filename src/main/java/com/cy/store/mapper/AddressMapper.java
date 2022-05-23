package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-21-9:57
 */
public interface AddressMapper {
    /**
     * 插入用户的收货地址
     * @param address 收货地址
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址的数量
     * @param uid 用户的uid
     * @return 当前用户的收获地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用过户的id查询用户的收货地址数据
     * @param uid
     * @return 地址集合
     */
    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);

    Integer updateNonDefaultByUid(Integer uid);

    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据收货地址id删除数据
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询某用户最后修改的收货地址
     * @param uid 归属的用户id
     * @return 该用户最后修改的收货地址，如果该用户没有收货地址数据则返回null
     */
    Address findLastModified(Integer uid);
}
