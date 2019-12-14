package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface SysPermissionExtendMapper extends MyMapper<SysPermission> {
    SysPermission findByRoleId(@Param("id") Long id);
}