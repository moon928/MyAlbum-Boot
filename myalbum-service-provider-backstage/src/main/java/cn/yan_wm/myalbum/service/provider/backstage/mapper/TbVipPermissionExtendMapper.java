package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface TbVipPermissionExtendMapper extends MyMapper<TbVipPermission> {
    int add(TbVipPermission vipPermission);

    int deleteById(@Param("id") Long id);

    int update(TbVipPermission vipPermission);

    TbVipPermission findById(@Param("id") Long id);
}