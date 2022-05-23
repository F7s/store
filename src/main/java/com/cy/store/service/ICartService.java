package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-03-05-19:07
 */
public interface ICartService {
    void addToCart(Integer uid,Integer pid,Long price,Integer number,String username);
    List<CartVO> getByUid(Integer uid);
    Integer addNum(Integer cid, Integer uid,String username);
    List<CartVO> getVOByCid(Integer uid,Integer[] cids);
}
