package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-15-18:24
 */
//表示整个类都是一个测试类，不会随着项目一起打包
@SpringBootTest
//@RunWith:表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(10);
        address.setPhone("31280712389");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer integer = addressMapper.countByUid(13);
        System.out.println(integer);
    }

    @Test
    public void findByUid(){
        List<Address> addresses = addressMapper.findByUid(10);
        addresses.forEach(System.out::println);
    }

    @Test
    public void findByAid(){
        Address byAid = addressMapper.findByAid(1);
        System.out.println(byAid);
    }

    @Test
    public void updateNonDefaultByUid(){
        addressMapper.updateNonDefaultByUid(10);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(4, "管理员", new Date());
    }
}
