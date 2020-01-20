package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysPermissionExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysPermissionMapper extends MyMapper<SysPermission> {

    /**
     * Describe this class
     *
     * @param id	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:25
     */
    SysPermissionExtend findByRoleId(@Param("id") Long id);

    /**
     * 获取全部的权限列表
     * @return
     */
    List<SysPermissionExtend> findAll();
}