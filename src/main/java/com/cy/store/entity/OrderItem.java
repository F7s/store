package com.cy.store.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizhenghao
 * @create 2022-03-06-17:16
 */
@Data
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}
