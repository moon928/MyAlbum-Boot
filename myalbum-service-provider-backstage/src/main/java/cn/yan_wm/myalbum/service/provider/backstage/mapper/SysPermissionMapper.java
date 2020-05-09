package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysPermissionExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 系统权限Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysPermissionMapper extends MyMapper<SysPermission> {

    /**
     * Describe this class
     *
     * @param id
     * @return
     * @throws
     * @author Yzn_zt
     * @date 2020/1/9 14:25
     */
    SysPermissionExtend findByRoleId(@Param("id") Long id);

    /**
     * 获取全部的权限列表
     *
     * @return
     */
    List<SysPermissionExtend> findAll();

    /**
     * 通过网关前缀查询权限列表
     *
     * @param zuulPrefix
     * @param principal 登陆人的用户名
     * @return
     */
    List<SysPermission> getSysPermissionByZuulPrefix(@Param("zuulPrefix") String zuulPrefix,@Param("principal") String principal);

    List<SysPermission> listPermissionByParentId(@Param("parentId") Integer parentId);
}