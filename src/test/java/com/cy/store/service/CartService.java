package com.cy.store.service;

import com.cy.store.mapper.CartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lizhenghao
 * @create 2022-03-05-19:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartService {

    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart(){
        cartService.addToCart(10, 10000012, 60L,30, "管理员");
    }

    @Test
    public void addNum(){
        cartService.addNum(7, 10, "管理员");
    }

}
