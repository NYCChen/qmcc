package com.qmcc.sys.mapper;

import com.qmcc.sys.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nyc
 * @since 2020-03-19
 */
public interface RoleMapper extends BaseMapper<Role> {

    void deleteRolePermissionByRid(Serializable id);

    void deleteRoleUserByRid(Serializable id);

    /**
     * 根据角色ID查询当前角色拥有的所有权限或菜单Id
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param pid
     */
    void saveRolePermission(@Param("rid")Integer rid, @Param("pid")Integer pid);
}
