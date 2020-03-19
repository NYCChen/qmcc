package com.qmcc.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qmcc.sys.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * //根据权限或菜单ID删除权限表和角色的关系表里的数据
     * @param id
     */
    void deleteRolePermissionByPid(@Param("id") Serializable id);
}
