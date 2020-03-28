package com.qmcc.sys.service;

import com.qmcc.sys.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyc
 * @since 2020-03-19
 */
public interface RoleService extends IService<Role> {

    /**
     * 1.根据角色ID查询当前角色拥有的所有权限或菜单Id
     * 2.根据查询出来的权限或菜单Id 查询权限和菜单数据
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     */
    void saveRolePermission(Integer rid, Integer[] ids);

    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    List<Integer> queryUserRoleIdsByUid(Integer id);
}
