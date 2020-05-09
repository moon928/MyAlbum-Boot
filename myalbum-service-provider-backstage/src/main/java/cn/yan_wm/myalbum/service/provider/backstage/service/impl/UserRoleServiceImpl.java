package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysUserRoleMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
/**
 * @program: MyAlbum-Boot
 * @description: 用户角色ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements UserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public Mapper<SysUserRole> getMapper() {
        return sysUserRoleMapper;
    }

    @Override
    public int insert(Integer userId, Integer roleId) {
        int i = sysUserRoleMapper.add(userId, roleId);
        return i;
    }

    @Override
    public int deleteByUserId(Integer userId) {
        int i = sysUserRoleMapper.deleteByUserId(userId);
        return i;
    }

    @Override
    public int deleteByUserIdAndRoleId(Integer userId, Integer roleId) {
        int i = sysUserRoleMapper.deleteByUserIdAndRoleId(userId,roleId);
        return i;
    }
}
