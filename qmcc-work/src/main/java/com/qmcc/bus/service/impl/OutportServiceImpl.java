package com.qmcc.bus.service.impl;

import com.qmcc.bus.domain.Goods;
import com.qmcc.bus.domain.Inport;
import com.qmcc.bus.domain.Outport;
import com.qmcc.bus.mapper.GoodsMapper;
import com.qmcc.bus.mapper.InportMapper;
import com.qmcc.bus.mapper.OutportMapper;
import com.qmcc.bus.service.OutportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qmcc.sys.common.WebUtils;
import com.qmcc.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author nyc
 * @since 2020-04-03
 */
@Service
@Transactional
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addOutPort(Integer id, Integer number, String remark) {
        //1,根据进货单ID查询进货单信息
        Inport inport = this.inportMapper.selectById(id);
        //2,根据商品ID查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-number);
        this.goodsMapper.updateById(goods);
        //3,添加退货单信息
        Outport entity=new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setNumber(number);
        User user=(User) WebUtils.getSession().getAttribute("user");
        entity.setOperateperson(user.getName());
        entity.setOutportprice(inport.getInportprice());
        entity.setOutputtime(new Date());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);
    }

}
