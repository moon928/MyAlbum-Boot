package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbUserInfoExtendMapper extends MyMapper<UserInfoExtend> {

    //修改用户信息
    int updateUserInfo(TbUserInfo userInfo);

    UserInfoExtend findById(@Param("id") Long id);

    List<UserInfoExtend> findAll();

    List<UserInfoExtend> findByNickName(@Param("nickName") String nickName);
}