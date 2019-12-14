package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.mapper.SysAdminRoleMapper;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysAdminRoleServiceImpl extends BaseCrudServiceImpl<SysAdminRole, SysAdminRoleMapper> implements SysAdminRoleService<SysAdminRole> {
}
