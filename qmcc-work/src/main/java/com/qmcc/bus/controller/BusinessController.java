package com.qmcc.bus.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务路由
 */
@Controller
@RequestMapping("/bus")
public class BusinessController {

    /**
     * 跳转到客户管理
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到客户管理
     */
    @RequestMapping("toProviderManager")
    public String toProviderManager(){
        return "business/provider/providerManager";
    }


    /**
     * 跳转到商品管理
     */
    @RequestMapping("toGoodsManager")
    public String toGoodsManager(){
        return "business/goods/goodsManager";
    }

    /**
     * 跳转到进货管理
     */
    @RequestMapping("toInportManager")
    public String toInportManager(){
        return "business/inport/inportManager";
    }

}

