package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysUserExtendService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    /**
     * Describe this class
     *
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    SysUserExtend getByUsername(String username);

    /**
     * Describe this class
     *
     * @param id
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    SysUserExtend getById(Long id);

    /**
     * Describe this class
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    List<SysUserExtend> getAll();

    /**
     * sysUserExtendPage
     * @param pageNum
     * @param pageSize
     * @return
     */
    default PageInfo<T> sysUserExtendPage(int pageNum,int pageSize) {
        return null;
    }
}
