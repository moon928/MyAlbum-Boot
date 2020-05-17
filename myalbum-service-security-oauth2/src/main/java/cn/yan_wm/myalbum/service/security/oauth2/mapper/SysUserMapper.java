package cn.yan_wm.myalbum.service.security.oauth2.mapper;

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
     * Describe this class
     *
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    SysUserExtend findbyUsername(@Param("username") String username);

}