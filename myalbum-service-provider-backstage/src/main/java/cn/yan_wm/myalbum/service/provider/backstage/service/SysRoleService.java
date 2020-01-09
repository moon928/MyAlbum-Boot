package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

import java.util.List;

public interface SysRoleService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    /**
     * Describe this class
     *
     * @param role	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:09
     */
    int add(SysRole role);

    /**
     * Describe this class
     *
     * @param id
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:09
     */
    int deleteById(Long id);
    
    /**
     * Describe this class
     *
     * @param role
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:09
     */
    int update(SysRole role);

    /**
     * Describe this class
     *
     * @param id
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:09
     */
    SysRoleExtend getById(Long id);

    /**
     * Describe this class
     *
     * @param
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:13
     */
    List<SysRoleExtend> getAll();
}
