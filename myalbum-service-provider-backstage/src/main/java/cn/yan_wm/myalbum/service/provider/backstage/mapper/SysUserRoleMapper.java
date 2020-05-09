package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;
/**
 * @program: MyAlbum-Boot
 * @description: 用户角色数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysUserRoleMapper extends MyMapper<SysUserRole> {

    /**
     * 为用户授权
     * @param userId
     * @param roleId
     * @return
     */
    int add(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 通过用户id删除用户角色
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId") Integer userId);

    /**
     * 删除用户指定角色
     * @param userId
     * @param roleId
     * @return
     */
    int deleteByUserIdAndRoleId(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
}