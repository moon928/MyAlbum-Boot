package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface SysUserRoleMapper extends MyMapper<SysUserRole> {

    /**
     * 为用户授权
     * @param userId
     * @param roleId
     * @return
     */
    int add(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 通过用户id删除用户角色
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId") Long userId);

    /**
     * 删除用户指定角色
     * @param userId
     * @param roleId
     * @return
     */
    int deleteByUserIdAndRoleId(@Param("userId") Long userId,@Param("roleId") Long roleId);
}