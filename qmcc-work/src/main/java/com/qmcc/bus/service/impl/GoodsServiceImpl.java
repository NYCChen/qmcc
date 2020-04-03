package com.qmcc.bus.service.impl;

import com.qmcc.bus.domain.Goods;
import com.qmcc.bus.mapper.GoodsMapper;
import com.qmcc.bus.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author nyc
 * @since 2020-03-29
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Override
    public boolean save(Goods entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Goods entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Goods getById(Serializable id) {
        return super.getById(id);
    }
}
