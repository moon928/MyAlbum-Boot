package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysUserExtendMapper extends MyMapper<SysUserExtend> {
    SysUserExtend findbyUsername(@Param("username") String username);

    SysUserExtend findbyId(@Param("id") Long id);

    List<SysUserExtend> findAll();
}