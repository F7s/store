package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author lizhenghao
 * @create 2022-03-05-17:32
 */
@SpringBootTest
//@RunWith:表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(10);
        cart.setNum(3);
        cart.setPid(10000005);
        cart.setPrice(241L);
        cartMapper.insert(cart);

    }

    @Test
    public void updateNumberByCid(){
        cartMapper.updateNumByCid(1, 33, "管理员", new Date());
    }

    @Test
    public void findByUidAndPid(){
        Cart cart = cartMapper.findByUidAndPid(10, 10000005);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid(){
        System.out.println(cartMapper.findByUid(10));
    }

    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(7));
    }

    @Test
    public void findVOByCid(){
        Integer[] cids = {1,2,4,2,7};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
