package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
/**
 * @program: MyAlbum-Boot
 * @description: 系统管理员角色Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysAdminRoleService {

    /**
     * 为管理员添加角色
     * @param adminId
     * @param roleId
     * @return
     */
    int insert(Long adminId, Long roleId);
}
