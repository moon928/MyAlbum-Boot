package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface SysAdminRoleMapper extends MyMapper<SysAdminRole> {

    /**
     * 为管理员授权
     * @param adminId
     * @param roleId
     * @return
     */
    int add(@Param("adminId") Long adminId, @Param("roleId") Long roleId);
}