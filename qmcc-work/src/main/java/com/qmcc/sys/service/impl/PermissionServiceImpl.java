package com.qmcc.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qmcc.sys.domain.Permission;
import com.qmcc.sys.mapper.PermissionMapper;
import com.qmcc.sys.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public boolean removeById(Serializable id) {
        PermissionMapper permissionMapper = this.getBaseMapper();
        //根据权限或菜单ID删除权限表和角色的关系表里的数据
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);//删除权限表的数据
    }
}
