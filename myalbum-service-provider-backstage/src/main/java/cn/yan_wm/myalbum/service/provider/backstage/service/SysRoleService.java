package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 系统角色Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysRoleService {
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

    /**
     * 分页查角色
     * @param page
     * @return
     */
    DataSet<SysRoleExtend> page(Page page);
}
