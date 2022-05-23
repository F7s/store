package com.cy.store.Controller;

import com.cy.store.entity.BaseEntity;
import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.service.impl.OrderServiceImpl;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author lizhenghao
 * @create 2022-03-06-19:39
 */
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        Order data = orderService.create(aid, cids, getuidFromSession(session), getUsernameFromSession(session));

        return new JsonResult<>(OK,data);
    }

}
