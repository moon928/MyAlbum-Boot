package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

import java.util.List;

public interface SysRoleService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    int add(SysRole role);

    int deleteById(Long id);

    int update(SysRole role);

    SysRoleExtend getById(Long id);

    List<SysRoleExtend> getAll();
}
