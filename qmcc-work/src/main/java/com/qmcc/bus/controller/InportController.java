package com.qmcc.bus.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qmcc.bus.domain.Goods;
import com.qmcc.bus.domain.Inport;
import com.qmcc.bus.domain.Provider;
import com.qmcc.bus.service.GoodsService;
import com.qmcc.bus.service.InportService;
import com.qmcc.bus.service.ProviderService;
import com.qmcc.sys.common.DataGridView;
import com.qmcc.sys.common.ResultObj;
import com.qmcc.sys.common.WebUtils;
import com.qmcc.sys.domain.Loginfo;
import com.qmcc.sys.domain.User;
import com.qmcc.bus.vo.InportVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author nyc
 * @since 2020-04-03
 */
@RestController
@RequestMapping("inport")
public class InportController {
    @Autowired
    private InportService inportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询
     */
    @RequestMapping("loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo) {
        IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(inportVo.getProviderid()!=null&&inportVo.getProviderid()!=0,"providerid",inportVo.getProviderid());
        queryWrapper.eq(inportVo.getGoodsid()!=null&&inportVo.getGoodsid()!=0,"goodsid",inportVo.getGoodsid());
        queryWrapper.ge(inportVo.getStartTime()!=null, "inporttime", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime()!=null, "inporttime", inportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getOperateperson()), "operateperson", inportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getRemark()), "remark", inportVo.getRemark());
        queryWrapper.orderByDesc("inporttime");
        this.inportService.page(page, queryWrapper);
        List<Inport> records = page.getRecords();
        for (Inport inport : records) {
            Provider provider = this.providerService.getById(inport.getProviderid());
            if(null!=provider) {
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = this.goodsService.getById(inport.getGoodsid());
            if(null!=goods) {
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }
    /**
     * 微信小程序查询
     */
    @RequestMapping("weloadAllInport")
    public DataGridView weloadAllInport(InportVo inportVo) {

        List<Inport> inportList = new ArrayList<>();
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("inporttime");
        inportList = this.inportService.list(queryWrapper);
        for (Inport inport : inportList) {
            Provider provider = this.providerService.getById(inport.getProviderid());
            if(null!=provider) {
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = this.goodsService.getById(inport.getGoodsid());
            if(null!=goods) {
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(inportList);
    }

    /**
     * 添加
     */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo) {
        try {
            inportVo.setInporttime(new Date());
            User user=(User) WebUtils.getSession().getAttribute("user");
            if (null != user){
                inportVo.setOperateperson(user.getName());
            }
            this.inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(Inport inport) {
        try {
            this.inportService.updateById(inport);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 微信小程序根据Id查询一个进货信息
     */
    @RequestMapping("weloadOneInport")
    public Map<String, Object> weloadOneInport(Integer id) {

        Map<String, Object> inportMap = new HashMap<>();
        Inport inport  = this.inportService.getById(id);
        Provider provider = this.providerService.getById(inport.getProviderid());
        if(null!=provider) {
            inport.setProvidername(provider.getProvidername());
        }
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        if(null!=goods) {
            inport.setGoodsname(goods.getGoodsname());
            inport.setSize(goods.getSize());
        }
        inportMap.put("inport", inport);
        return inportMap;
    }
    /**
     * 删除
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id) {
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

