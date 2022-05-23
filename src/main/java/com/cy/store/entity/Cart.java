package com.cy.store.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizhenghao
 * @create 2022-03-05-17:02
 */
@Data
public class Cart extends BaseEntity implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
}
