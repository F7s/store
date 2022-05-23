package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-21-18:24
 */
public interface IDistrictService {
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
