package com.qmcc.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qmcc.bus.domain.Customer;
import com.qmcc.bus.domain.Goods;
import com.qmcc.bus.domain.Sales;
import com.qmcc.bus.service.CustomerService;
import com.qmcc.bus.service.GoodsService;
import com.qmcc.bus.service.SalesService;
import com.qmcc.bus.vo.SalesVo;
import com.qmcc.sys.common.DataGridView;
import com.qmcc.sys.common.ResultObj;
import com.qmcc.sys.common.WebUtils;
import com.qmcc.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nyc
 * @since 2020-04-06
 */
@RestController
@RequestMapping("sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有商品销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("loadAllSales")
    public DataGridView loadAllSales(SalesVo salesVo){
        IPage<Sales> page = new Page<>(salesVo.getPage(),salesVo.getLimit());
        QueryWrapper<Sales> queryWrapper = new QueryWrapper<Sales>();
        //根据客户进行模糊查询
        queryWrapper.eq(salesVo.getCustomerid()!=null&&salesVo.getCustomerid()!=0,"customerid",salesVo.getCustomerid());
        //根据商品模糊查询
        queryWrapper.eq(salesVo.getGoodsid()!=null&&salesVo.getGoodsid()!=0,"goodsid",salesVo.getGoodsid());
        //根据时间进行模糊查询
        queryWrapper.ge(salesVo.getStartTime()!=null,"salestime",salesVo.getStartTime());
        queryWrapper.le(salesVo.getEndTime()!=null,"salestime",salesVo.getEndTime());
        IPage<Sales> page1 = salesService.page(page, queryWrapper);
        List<Sales> records = page1.getRecords();
        for (Sales sales : records) {
            //设置客户姓名
            Customer customer = customerService.getById(sales.getCustomerid());
            sales.setCustomername(customer.getCustomername());
            //设置商品名称
            Goods goods = goodsService.getById(sales.getGoodsid());
            sales.setGoodsname(goods.getGoodsname());
            //设置商品规格
            sales.setSize(goods.getSize());
        }
        return new DataGridView(page1.getTotal(),page1.getRecords());
    }


    /**
     * 添加商品销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("addSales")
    public ResultObj addSales(SalesVo salesVo){
        try {
            //获得当前系统用户
            User user = (User) WebUtils.getSession().getAttribute("user");
            //设置操作人
            if(null != user){
                salesVo.setOperateperson(user.getName());
            }
            //设置销售时间
            salesVo.setSalestime(new Date());
            salesService.save(salesVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新商品销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("updateSales")
    public ResultObj updateSales(SalesVo salesVo){
        try {
            salesService.updateById(salesVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }
    /**
     * 微信小程序根据Id查询一个销售信息
     */
    @RequestMapping("weloadOneSales")
    public Map<String, Object> weloadOneSales(Integer id) {

        Map<String, Object> inportMap = new HashMap<>();
        Sales sales  = this.salesService.getById(id);
        Customer customer = this.customerService.getById(sales.getCustomerid());
        if(null!=customer) {
            sales.setCustomername(customer.getCustomername());
        }
        Goods goods = this.goodsService.getById(sales.getGoodsid());
        if(null!=goods) {
            sales.setGoodsname(goods.getGoodsname());
            sales.setSize(goods.getSize());
        }
        inportMap.put("sales", sales);
        return inportMap;
    }

    /**
     * 删除商品销售信息
     * @param id
     * @return
     */
    @RequestMapping("deleteSales")
    public ResultObj deleteSales(Integer id){
        try {
            salesService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

