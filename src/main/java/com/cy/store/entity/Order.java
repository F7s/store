package com.cy.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lizhenghao
 * @create 2022-03-06-17:15
 */
@Data
public class Order extends BaseEntity implements Serializable {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
}
