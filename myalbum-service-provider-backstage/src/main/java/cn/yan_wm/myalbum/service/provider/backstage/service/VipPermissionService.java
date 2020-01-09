package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

import java.util.List;

public interface VipPermissionService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
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
