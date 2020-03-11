package com.qmcc.sys.service.impl;

import com.qmcc.sys.domain.User;
import com.qmcc.sys.mapper.UserMapper;
import com.qmcc.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
