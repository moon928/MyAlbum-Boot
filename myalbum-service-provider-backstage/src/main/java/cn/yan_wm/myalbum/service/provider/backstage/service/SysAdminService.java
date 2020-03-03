package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 系统管理员Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysAdminService{

    /**
     * 验证值是否存在
     * @param property
     * @param value
     * @return
     */
    boolean unique(String property, String value);

    /**
     * 保存管理员信息
     * @param sysAdmin
     * @return
     */
    SysAdmin save(SysAdmin sysAdmin);

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
     * @param page
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:07
     */
     DataSet<SysAdminExtend> page(Page page);
}
