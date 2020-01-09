package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface SysPermissionExtendMapper extends MyMapper<SysPermission> {
    /**
     * Describe this class
     *
     * @param id	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:25
     */
    SysPermission findByRoleId(@Param("id") Long id);
}