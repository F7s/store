package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AccessException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-03-05-19:12
 */
@Service
public class CartService implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addToCart(Integer uid, Integer pid,Long price,Integer number, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if (result == null){
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(number);
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            cart.setPrice(price);
            Integer rows = cartMapper.insert(cart);
            if (rows != 1){
                throw new InsertException("插入数据时产生未知异常");
            }
        }else {
            Integer rows = cartMapper.updateNumByCid(result.getCid(), number+result.getNum(), username, new Date());
            if (rows != 1){
                throw new UpdateException("更新数据时产生未知异常");
            }
        }
    }

    @Override
    public List<CartVO> getByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid,String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null){
            throw new CartNotFoundException();
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1){
            throw new UpdateException("更新数量时出现异常");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);

        Iterator<CartVO> iterator = list.iterator();
        while (iterator.hasNext()){
            CartVO cartVO = iterator.next();
            if (!cartVO.getUid().equals(uid)){
                list.remove(cartVO);
            }
        }
        return list;
    }
}
