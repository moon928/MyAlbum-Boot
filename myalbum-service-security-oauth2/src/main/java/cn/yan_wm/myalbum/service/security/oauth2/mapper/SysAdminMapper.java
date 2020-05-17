package cn.yan_wm.myalbum.service.security.oauth2.mapper;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 系统管理员数据库操作Mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SysAdminMapper extends MyMapper<SysAdminExtend> {

    /**
     * Describe this class
     *
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/7 19:58
     */
    SysAdminExtend findByUsername(@Param("username") String username);


}
