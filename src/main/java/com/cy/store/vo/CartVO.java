package com.cy.store.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizhenghao
 * @create 2022-03-06-7:28
 */
@Data
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;
}
