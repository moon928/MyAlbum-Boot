package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: vip权限Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface VipPermissionService{
    /**
     * Describe this class
     *
     * @param vipPermission
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    int add(TbVipPermission vipPermission);

    /**
     * Describe this class
     *
     * @param id
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    int deleteById(Long id);

    /**
     * Describe this class
     *
     * @param vipPermission
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    int update(TbVipPermission vipPermission);

}
