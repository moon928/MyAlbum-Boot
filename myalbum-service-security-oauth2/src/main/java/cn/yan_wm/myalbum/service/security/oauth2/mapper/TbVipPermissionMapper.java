package cn.yan_wm.myalbum.service.security.oauth2.mapper;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

/**
 * @program: MyAlbum-Boot
 * @description: 系统vip权限数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbVipPermissionMapper extends MyMapper<TbVipPermission> {
    /**
     * Describe this class
     *
     * @param id
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    TbVipPermission findById(@Param("id") Integer id);
}