package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import cn.yan_wm.myalbum.commons.mapper.SysUserRoleMapper;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserRoleServiceImpl extends BaseCrudServiceImpl<SysUserRole, SysUserRoleMapper> implements UserRoleService<SysUserRole> {
}
