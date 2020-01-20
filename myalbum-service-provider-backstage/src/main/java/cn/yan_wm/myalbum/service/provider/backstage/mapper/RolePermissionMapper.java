package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysRolePermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface RolePermissionMapper extends MyMapper<SysRolePermission> {
    /**
     * 通过角色id查权限id
     * @param roleId
     * @return
     */
    List<SysRolePermission> findPermissionIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 通过角色id删除所有的权限
     * @param roleId
     * @return
     */
    int deletePermissionByRoleId(@Param("roleId") Long roleId);

    /**
     * 为角色授权
     * @param roleId
     * @param permissionIds
     * @return
     */
    int addPermissionForRole(@Param("roleId") Long roleId,@Param("permissionIds") Long[] permissionIds);
}
