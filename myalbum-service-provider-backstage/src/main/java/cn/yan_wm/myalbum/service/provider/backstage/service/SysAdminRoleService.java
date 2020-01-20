package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

public interface SysAdminRoleService {

    /**
     * 为管理员添加角色
     * @param adminId
     * @param roleId
     * @return
     */
    int insert(Long adminId,Long roleId);
}
