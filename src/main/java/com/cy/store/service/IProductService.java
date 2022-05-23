package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-24-10:25
 */
public interface IProductService {
    List<Product> findHotList();
    Product findById(Integer id);
}
