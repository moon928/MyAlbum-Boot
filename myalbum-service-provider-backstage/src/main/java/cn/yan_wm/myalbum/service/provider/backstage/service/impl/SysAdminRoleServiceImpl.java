package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysAdminRoleMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SysAdminRoleServiceImpl extends BaseServiceImpl<SysAdminRole> implements SysAdminRoleService {

    @Autowired
    private SysAdminRoleMapper sysAdminRoleMapper;
    @Override
    public Mapper<SysAdminRole> getMapper() {
        return sysAdminRoleMapper;
    }

    @Override
    public int insert(Long adminId,Long roleId) {
        int i = sysAdminRoleMapper.add(adminId, roleId);
        return i;
    }
}
