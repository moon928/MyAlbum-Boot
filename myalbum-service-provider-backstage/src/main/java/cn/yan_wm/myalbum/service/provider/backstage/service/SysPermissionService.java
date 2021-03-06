package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysPermissionExtend;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 系统权限ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysPermissionService {
    /**
     * Describe this class
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 16:06
     */
    public List<SysPermissionExtend> getSysPermissionTree();

    /**
     * 添加权限
     * @param sysPermission
     * @return
     */
    SysPermission save(SysPermission sysPermission);

    /**
     * 通过id删除权限
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 通过网关前缀名查询权限列表
     * @param zuulPrefix
     * @return
     */
    List<SysPermission> getSysPermissionByZuulPrefix(String zuulPrefix,String principal);

    String note(Integer root);
}
