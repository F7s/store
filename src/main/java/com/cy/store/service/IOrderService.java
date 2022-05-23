package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;

/**
 * @author lizhenghao
 * @create 2022-03-06-18:59
 */
public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
