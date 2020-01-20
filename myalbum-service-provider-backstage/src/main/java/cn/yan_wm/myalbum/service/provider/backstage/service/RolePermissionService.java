package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysRolePermission;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 角色权限控制
 * @author: yan_zt
 * @create: 2020-01-09 15:45
 */

public interface RolePermissionService{
    /**
     * 通过角色id查权限id
     * @param roleId
     * @return
     */
    Integer[] getPermissionIdsByRoleId(Long roleId);

    /**
     * 通过角色id删除该角色的所有权限
     * @param roleId
     * @return
     */
    int deletePermissionByRoleId(Long roleId);

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    int addpermissionForRole(Long roleId, Long[] permissionIds);
}
