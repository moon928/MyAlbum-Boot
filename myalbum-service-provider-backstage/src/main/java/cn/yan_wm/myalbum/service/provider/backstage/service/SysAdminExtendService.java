package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysAdminExtendService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
     /**
      * Describe this class
      *
      * @param username
      * @exception
      * @return
      * @author Yzn_zt
      * @date 2020/1/9 14:06
      */
    SysAdminExtend getByUsername(String username);

    /**
     * Describe this class
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:07
     */
    List<SysAdminExtend> getAll();

    /**
     * Describe this class
     *
     * @param pageNum
     * @param pageSize
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:07
     */
    default PageInfo<T> adminExtendPage(int pageNum, int pageSize) {
        return null;
    }
}
