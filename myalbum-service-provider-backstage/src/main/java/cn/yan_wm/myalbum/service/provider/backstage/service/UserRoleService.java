package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

public interface UserRoleService {

    /**
     * 为用户授权
     * @param userId
     * @param roleId
     * @return
     */
    int insert(Long userId, Long roleId);

    /**
     * 通过用户id删除用户角色
     * @param userId
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 删除用户指定角色
     * @param userId
     * @param roleId
     * @return
     */
    int deleteByUserIdAndRoleId(Long userId,Long roleId);
}
