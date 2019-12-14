package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface SysAdminExtendMapper extends MyMapper<SysAdminExtend> {
//    int updateAdmin(SysAdmin sysAdmin);

    SysAdminExtend findByUsername(@Param("username") String username);

    List<SysAdminExtend> findAll();

}
