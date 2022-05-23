package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @author lizhenghao
 * @create 2022-02-21-17:45
 */
public interface DistrictMapper {
    List<District> findByParent(String parent);

    String findNameByCode(String code);


}
