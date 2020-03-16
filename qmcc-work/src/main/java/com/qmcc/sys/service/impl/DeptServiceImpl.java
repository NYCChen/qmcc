package com.qmcc.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.qmcc.sys.domain.Dept;
import com.qmcc.sys.mapper.DeptMapper;
import com.qmcc.sys.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyc
 * @since 2020-03-14
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public Dept getOne(Wrapper<Dept> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean updateById(Dept entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

}
