package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysUserExtendMapper extends MyMapper<SysUserExtend> {
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
}