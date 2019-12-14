package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbUserFanExtendMapper extends MyMapper<UserFanExtend> {
    int addFan(@Param("userId") Long userId,@Param("fanId") Long fanId);

    int deleteFan(@Param("userId") Long userId,@Param("fanId") Long fanId);

    int updateFanNote(@Param("userId") Long userId,@Param("fanId") Long fanId,@Param("note") String note);

    UserFanExtend findFanByFanId(@Param("userId") Long userId,@Param("fanId") Long fanId);

    List<UserFanExtend> findAll(@Param("userId") Long userId);
}