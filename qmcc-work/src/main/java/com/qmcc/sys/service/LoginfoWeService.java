package com.qmcc.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qmcc.sys.domain.Loginfo;
import com.qmcc.sys.vo.LoginfoWeVo;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyc
 * @since 2020-03-12
 */
public interface LoginfoWeService extends IService<LoginfoWeVo> {

    @Override
    default boolean saveBatch(Collection<LoginfoWeVo> entityList, int batchSize) {
        return false;
    }


}
