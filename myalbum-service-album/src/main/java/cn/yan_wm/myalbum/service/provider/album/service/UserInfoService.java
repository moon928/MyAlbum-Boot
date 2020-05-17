package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;

/**
 * @program: MyAlbum-Boot
 * @description: 用户基本信息Service
 * @author: yan_zt
 * @create: 2020-05-10 02:56
 */
public interface UserInfoService {
    /**
     * 通过id获取用户信息（id，头像，昵称）
     * @param id
     * @return
     */
    UserInfoExtend getUserInfoById(Integer id);

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
    int updateUserAvatar(Integer userId,String avatar);
}
