package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysRolePermission;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.RolePermissionMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 角色权限控制 的实现类
 * @author: yan_zt
 * @create: 2020-01-09 16:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class RolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Mapper<SysRolePermission> getMapper() {
        return rolePermissionMapper;
    }
    @Override
    public Integer[] getPermissionIdsByRoleId(Integer roleId) {
        List<SysRolePermission> rolePermissions = rolePermissionMapper.findPermissionIdsByRoleId(roleId);
        Integer[] integers = new Integer[rolePermissions.size()];
        int i = 0;
        for (SysRolePermission item : rolePermissions){
            integers[i++] = item.getPermissionId();
        }
        return integers;
    }

    @Transactional(readOnly = false)
    @Override
    public int deletePermissionByRoleId(Long roleId) {
        int i = rolePermissionMapper.deletePermissionByRoleId(roleId);
        return i;
    }

    @Transactional(readOnly = false)
    @Override
    public int addpermissionForRole(Long roleId, Long[] permissionIds) {
        int i = rolePermissionMapper.addPermissionForRole(roleId, permissionIds);
        return i;
    }


}
