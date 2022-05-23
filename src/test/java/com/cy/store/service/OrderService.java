package com.cy.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lizhenghao
 * @create 2022-03-06-20:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderService {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create(){
        Integer[] cids = {7,8};
        System.out.println(orderService.create(1, cids, 10, "abc"));
    }
}
