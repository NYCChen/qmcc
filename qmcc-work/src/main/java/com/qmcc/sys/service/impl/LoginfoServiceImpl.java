package com.qmcc.sys.service.impl;

import com.qmcc.sys.domain.Loginfo;
import com.qmcc.sys.mapper.LoginfoMapper;
import com.qmcc.sys.service.LoginfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyc
 * @since 2020-03-12
 */
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

}
