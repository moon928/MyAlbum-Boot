package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysUserMapper extends MyMapper<SysUserExtend> {

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    int add(SysUser sysUser);

    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */

    int update(SysUser sysUser);

    /**
     * 通过id修改用户密码
     * @param id
     * @param password
     * @return
     */
    int updatePassword(@Param("id") Long id,@Param("password") String password);

    /**
     * 修改用户名
     * @param id
     * @param username
     * @return
     */
    int updateUsername(@Param("id") Long id,@Param("username") String username);
    /**
     * 更新用户状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") Long id,@Param("status") int status);
    /**
     * Describe this class
     *
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    SysUserExtend findbyUsername(@Param("username") String username);

    /**
     * Describe this class
     *
     * @param id
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    SysUserExtend findbyId(@Param("id") Long id);

    /**
     * Describe this class
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:13
     */
    List<SysUserExtend> findAll();

    /**
     * 查询过去第几天的新增用户量
     *
     * @param day
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/10 10:56
     */
     BackstageIndexDto getNewUsers(@Param("day") int day);

     /**
      * 查询过去第n个月的新增用户量
      *
      * @param 	n
      * @exception
      * @return
      * @author Yzn_zt
      * @date 2020/1/10 17:12
      */
     BackstageIndexDto getNewUsersMonth(@Param("n") int n);
}