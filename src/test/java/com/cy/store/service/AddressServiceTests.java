package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lizhenghao
 * @create 2022-02-21-15:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("173482239");
        address.setName("女朋友");
        addressService.addNewAddress(10, "管理员",address);
    }

    @Test
    public void updateDefault(){
        addressService.setDefault(1, 10, "管理员");
    }

    @Test
    public void delete(){
        addressService.deleteAddress(4,10,"管理员");
    }
}
