package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRoleExtend> {
    /**
     * Describe this class
     *
     * @param role	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:08
     */
    int add(SysRole role);

    /**
     * Describe this class
     *
     * @param id	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:08
     */
    int deleteById(@Param("id") Long id);

    /**
     * Describe this class
     *
     * @param role	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:08
     */
    int update(SysRole role);

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

    /**
     * findById
     * @param id
     * @return
     */
    SysRoleExtend findById(@Param("id") Long id);

    /**
     * Describe this class
     *
     * @param 	
     * @exception 
     * @return 
     * @author Yzn_zt
     * @date 2020/1/9 14:24
     */
    List<SysRoleExtend> findAll();

    /**
     * 查找所有VIP的id
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/10 13:56
     */
    List<SysRole> findVipIds();

    /**
     * 获取会员总人数
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/10 14:03
     */
    int getTotal();

    int getVipNumByRoleId(@Param("roleId") Long roleId);
}