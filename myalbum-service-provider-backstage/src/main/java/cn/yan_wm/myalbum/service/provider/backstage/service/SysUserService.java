package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysUserService{

    /**
     * 验证值是否存在
     * @param property
     * @param value
     * @return
     */
    boolean unique(String property, String value);

    /**
     * 保存用户信息
     * @param sysUser
     * @return
     */
    SysUser save(SysUser sysUser);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    int updatePwd(Long id, String password);

    /**
     * 修改用户名
     * @param id
     * @param username
     * @return
     */
    int updateUsername(Long id, String username);
    /**
     * 更新用户状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, int status);
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
     * 分页获取用户信息
     * @param page
     * @return
     */
    DataSet<SysUserExtend> page(Page page);
}
