package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;
/**
 * @program: MyAlbum-Boot
 * @description: 系统管理员授予角色数据库操作Mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysAdminRoleMapper extends MyMapper<SysAdminRole> {

    /**
     * 为管理员授权
     * @param adminId
     * @param roleId
     * @return
     */
    int add(@Param("adminId") Long adminId, @Param("roleId") Long roleId);
}