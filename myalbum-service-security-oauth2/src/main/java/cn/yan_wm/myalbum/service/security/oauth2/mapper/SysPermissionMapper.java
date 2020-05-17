package cn.yan_wm.myalbum.service.security.oauth2.mapper;

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

}