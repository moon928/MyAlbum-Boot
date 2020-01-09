package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface TbVipPermissionExtendMapper extends MyMapper<TbVipPermission> {
    /**
     * Describe this class
     *
     * @param vipPermission
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    int add(TbVipPermission vipPermission);

    /**
     * Describe this class
     *
     * @param id
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    int deleteById(@Param("id") Long id);

    /**
     * Describe this class
     *
     * @param vipPermission
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    int update(TbVipPermission vipPermission);

    /**
     * Describe this class
     *
     * @param id
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:11
     */
    TbVipPermission findById(@Param("id") Long id);
}