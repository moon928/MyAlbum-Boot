package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysRoleExtendMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends BaseCrudServiceImpl<SysRoleExtend, SysRoleExtendMapper> implements SysRoleService<SysRoleExtend> {

    @Autowired
    private SysRoleExtendMapper sysRoleExtendMapper;

    @Transactional(readOnly = false)
    @Override
    public int add(SysRole role) {
        return sysRoleExtendMapper.add(role);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteById(Long id) {
        return sysRoleExtendMapper.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public int update(SysRole role) {
        return sysRoleExtendMapper.update(role);
    }

    @Override
    public SysRoleExtend getById(Long id) {
        return sysRoleExtendMapper.findById(id);
    }

    @Override
    public List<SysRoleExtend> getAll() {
        return sysRoleExtendMapper.findAll();
    }
}
