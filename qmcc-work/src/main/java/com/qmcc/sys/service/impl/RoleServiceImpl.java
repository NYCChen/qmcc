package com.qmcc.sys.service.impl;

import com.qmcc.sys.domain.Role;
import com.qmcc.sys.mapper.RoleMapper;
import com.qmcc.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyc
 * @since 2020-03-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public boolean removeById(Serializable id) {
        // 根据角色ID删除sys_role_permission
        this.getBaseMapper().deleteRolePermissionByRid(id);
        // 根据角色ID删除sys_role_user
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }

    /**
     * 根据角色ID查询当前角色拥有的所有权限或菜单Id
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }

    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {
        RoleMapper roleMapper = this.getBaseMapper();
        //根据rid删除sys_role_permission
        roleMapper.deleteRolePermissionByRid(rid);
        if(ids!=null&&ids.length>0) {
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid,pid);
            }
        }
    }
}
