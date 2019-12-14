package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

import java.util.List;

public interface VipPermissionService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    int add(TbVipPermission vipPermission);

    int deleteById(Long id);

    int update(TbVipPermission vipPermission);

}
