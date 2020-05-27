package com.qmcc.bus.service.impl;

import com.qmcc.bus.domain.Goods;
import com.qmcc.bus.domain.Inport;
import com.qmcc.bus.domain.Produce;
import com.qmcc.bus.mapper.GoodsMapper;
import com.qmcc.bus.mapper.ProduceMapper;
import com.qmcc.bus.service.ProduceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 9216 kB; (`providerid`) REFER `warehouse/bus_provider`(`id`); (`goo 服务实现类
 * </p>
 *
 * @author nyc
 * @since 2020-05-13
 */
@Service
public class ProduceServiceImpl extends ServiceImpl<ProduceMapper, Produce> implements ProduceService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Produce entity) {
        //根据货物编号查询商品
        Goods goods=goodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber()+entity.getNumber());
        goodsMapper.updateById(goods);
        //保存生产信息
        return super.save(entity);
    }

    @Override
    public boolean updateById(Produce entity) {
        //根据生产ID查询生产
        Produce produce = this.baseMapper.selectById(entity.getId());
        //根据货物ID查询商品货物信息
        Goods goods = this.goodsMapper.selectById(produce.getGoodsid());
        //库存的算法  当前库存-生产修改之前的数量+修改之后的数量
        goods.setNumber(goods.getNumber()-produce.getNumber()+entity.getNumber());
        this.goodsMapper.updateById(goods);
        //更新生产
        return super.updateById(entity);
    }

    /**
     * 删除某一货物生产信息
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        //根据生产ID查询此条生产信息
        Produce produce = this.baseMapper.selectById(id);
        //根据商品ID查询商品信息
        Goods goods = this.goodsMapper.selectById(produce.getGoodsid());
        //库存的算法  当前库存-生产数量
        goods.setNumber(goods.getNumber()-produce.getNumber());
        this.goodsMapper.updateById(goods);
        return super.removeById(id);
    }
}
