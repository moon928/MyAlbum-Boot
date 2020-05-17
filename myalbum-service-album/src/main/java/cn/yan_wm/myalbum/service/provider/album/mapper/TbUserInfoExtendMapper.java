package cn.yan_wm.myalbum.service.provider.album.mapper;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 用户基本信息数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbUserInfoExtendMapper extends MyMapper<UserInfoExtend> {

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    UserInfoExtend findById(@Param("id") Integer id);

    /**
     * 获取用户总数
     * @return
     */
    int countUser();

    /**
     * 更新用户头像
     * @param userId
     * @param avatar
     * @return
     */
    int updateUserAvatar(@Param("userId") Integer userId,@Param("avatar") String avatar);
}