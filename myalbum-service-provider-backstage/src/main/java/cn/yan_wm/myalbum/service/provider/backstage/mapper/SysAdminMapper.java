package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysAdminMapper extends MyMapper<SysAdminExtend> {


    /**
     * 添加管理员
     * @param sysAdmin
     * @return
     */
    int add(SysAdmin sysAdmin);

    /**
     * 修改管理员
     * @param sysAdmin
     * @return
     */

    int update(SysAdmin sysAdmin);
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

    /**
     * Describe this class
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/7 19:58
     */
    List<SysAdminExtend> findAll();

}
