package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
/**
 * @program: MyAlbum-Boot
 * @description: 用户角色Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface UserRoleService {

    /**
     * 为用户授权
     * @param userId
     * @param roleId
     * @return
     */
    int insert(Integer userId, Integer roleId);

    /**
     * 通过用户id删除用户角色
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);

    /**
     * 删除用户指定角色
     * @param userId
     * @param roleId
     * @return
     */
    int deleteByUserIdAndRoleId(Integer userId,Integer roleId);
}
