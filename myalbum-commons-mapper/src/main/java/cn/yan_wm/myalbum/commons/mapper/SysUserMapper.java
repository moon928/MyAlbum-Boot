package cn.yan_wm.myalbum.commons.mapper;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface SysUserMapper extends MyMapper<SysUser> {
    int updatePwd(@Param("id") Long id,@Param("password") String password);

    int updateStatus(@Param("id") Long id,@Param("status") int status);

    int updateUsername(@Param("id") Long id,@Param("username") String username);
}