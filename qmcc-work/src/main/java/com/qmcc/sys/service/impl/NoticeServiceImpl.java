package com.qmcc.sys.service.impl;

import com.qmcc.sys.domain.Notice;
import com.qmcc.sys.mapper.NoticeMapper;
import com.qmcc.sys.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyc
 * @since 2020-03-13
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
