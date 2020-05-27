package com.qmcc.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qmcc.bus.domain.Goods;
import com.qmcc.bus.domain.Produce;
import com.qmcc.bus.domain.Provider;
import com.qmcc.bus.service.GoodsService;
import com.qmcc.bus.service.ProduceService;
import com.qmcc.bus.service.ProviderService;
import com.qmcc.bus.vo.ProduceVo;
import com.qmcc.sys.common.DataGridView;
import com.qmcc.sys.common.ResultObj;
import com.qmcc.sys.common.WebUtils;
import com.qmcc.sys.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * produce
 * bus_produce
 * @author nyc
 * @since 2020-04-03
 */
@RestController
@RequestMapping("produce")
public class ProduceController {
    @Autowired
    private ProduceService produceService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询
     */
    @RequestMapping("loadAllProduce")
    public DataGridView loadAllProduce(ProduceVo produceVo) {
        IPage<Produce> page = new Page<>(produceVo.getPage(), produceVo.getLimit());
        QueryWrapper<Produce> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(produceVo.getGoodsid()!=null&&produceVo.getGoodsid()!=0,"goodsid",produceVo.getGoodsid());
        queryWrapper.ge(produceVo.getStartTime()!=null, "producetime", produceVo.getStartTime());
        queryWrapper.le(produceVo.getEndTime()!=null, "producetime", produceVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(produceVo.getProduceperson()), "produceperson", produceVo.getProduceperson());
        queryWrapper.like(StringUtils.isNotBlank(produceVo.getRemark()), "remark", produceVo.getRemark());
        queryWrapper.orderByDesc("producetime");
        this.produceService.page(page, queryWrapper);
        List<Produce> records = page.getRecords();
        for (Produce produce : records) {
            Goods goods = this.goodsService.getById(produce.getGoodsid());
            if(null!=goods) {
                produce.setGoodsname(goods.getGoodsname());
                produce.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    /**
     * 微信小程序查询
     */
    /*@RequestMapping("weloadAllProduce")
    public DataGridView weloadAllProduce(ProduceVo produceVo) {

        List<Produce> produceList = new ArrayList<>();
        QueryWrapper<Produce> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("producetime");
        produceList = this.produceService.list(queryWrapper);
        for (Produce produce : produceList) {
            Provider provider = this.providerService.getById(produce.getProviderid());
            if(null!=provider) {
                produce.setProvidername(provider.getProvidername());
            }
            Goods goods = this.goodsService.getById(produce.getGoodsid());
            if(null!=goods) {
                produce.setGoodsname(goods.getGoodsname());
                produce.setSize(goods.getSize());
            }
        }
        return new DataGridView(produceList);
    }*/

    /**
     * 添加
     */
    @RequestMapping("addProduce")
    public ResultObj addProduce(ProduceVo produceVo) {
        try {
            produceVo.setProducetime(new Date());
            //User user=(User) WebUtils.getSession().getAttribute("user");
            //produceVo.setProduceperson(user.getName());
            this.produceService.save(produceVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 微信小程序根据Id查询一个生产信息
     */
    @RequestMapping("weloadOneProduce")
    public Map<String, Object> weloadOneProduce(Integer id) {

        Map<String, Object> inportMap = new HashMap<>();
        Produce produce  = this.produceService.getById(id);
        /*Provider provider = this.providerService.getById(inport.getProviderid());
        if(null!=provider) {
            inport.setProvidername(provider.getProvidername());
        }*/
        Goods goods = this.goodsService.getById(produce.getGoodsid());
        if(null!=goods) {
            produce.setGoodsname(goods.getGoodsname());
            produce.setSize(goods.getSize());
        }
        inportMap.put("produce", produce);
        return inportMap;
    }

    /**
     * 修改
     */
    @RequestMapping("updateProduce")
    public ResultObj updateProduce(Produce produce) {
        try {
            this.produceService.updateById(produce);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除
     */
    @RequestMapping("deleteProduce")
    public ResultObj deleteProduce(Integer id) {
        try {
            this.produceService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

