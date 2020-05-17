package cn.yan_wm.myalbum.service.security.oauth2.mapper;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 系统角色数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysRoleMapper extends MyMapper<SysRoleExtend> {

    /**
     * Describe this class
     *
     * @param id	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:08
     */
    SysRoleExtend findByUserId(@Param("id") Long id);

    /**
            * Describe this class
     *
             * @param id
     * @exception
     * @return
             * @author Yzn_zt
     * @date 2020/1/9 14:08
            */
    SysRoleExtend findByAdminId(@Param("id") Long id);

}