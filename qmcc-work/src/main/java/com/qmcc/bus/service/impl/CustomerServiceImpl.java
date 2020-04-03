package com.qmcc.bus.service.impl;

import com.qmcc.bus.domain.Customer;
import com.qmcc.bus.mapper.CustomerMapper;
import com.qmcc.bus.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyc
 * @since 2020-03-28
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
