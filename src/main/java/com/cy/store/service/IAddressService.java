package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-21-11:09
 */
public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid,Integer uid,String username);

    void deleteAddress(Integer aid,Integer uid,String username);

    Address getByAid(Integer aid, Integer uid);
}
