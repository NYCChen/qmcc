package com.qmcc.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qmcc.sys.domain.Permission;
import com.qmcc.sys.mapper.PermissionMapper;
import com.qmcc.sys.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
