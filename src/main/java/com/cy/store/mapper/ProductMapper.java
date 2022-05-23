package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-24-10:23
 */
public interface ProductMapper {
    List<Product> findHotList();


    Product findById(Integer id);
}
