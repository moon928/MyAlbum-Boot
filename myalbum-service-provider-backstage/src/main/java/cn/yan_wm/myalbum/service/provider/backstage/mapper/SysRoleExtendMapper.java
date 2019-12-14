package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysRoleExtendMapper extends MyMapper<SysRoleExtend> {
    int add(SysRole role);

    int deleteById(@Param("id") Long id);

    int update(SysRole role);

    SysRoleExtend findByUserId(@Param("id") Long id);

    SysRoleExtend findByAdminId(@Param("id") Long id);

    SysRoleExtend findById(@Param("id") Long id);

    List<SysRoleExtend> findAll();
}