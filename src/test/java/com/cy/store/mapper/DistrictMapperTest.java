package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhenghao
 * @create 2022-02-21-17:52
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class DistrictMapperTest {
    @Autowired
    DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> dis = districtMapper.findByParent("86");
        for (District district : dis) {
            System.out.println(district);
        }
    }

    @Test
    public void findByCode(){
        String name = districtMapper.findNameByCode("110101");
        System.out.println(name);
    }
}