package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbUserInfoExtendMapper;
import cn.yan_wm.myalbum.service.provider.album.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: MyAlbum-Boot
 * @description: UserInfoService实现类
 * @author: yan_zt
 * @create: 2020-05-10 02:59
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoExtend> implements UserInfoService {

    @Autowired
    private TbUserInfoExtendMapper userInfoExtendMapper;

    @Override
    public Mapper<UserInfoExtend> getMapper() {
        return userInfoExtendMapper;
    }

    @Override
    public UserInfoExtend getUserInfoById(Integer id) {
        return userInfoExtendMapper.findById(id);
    }

    @Override
    public int countUser() {
        return userInfoExtendMapper.countUser();
    }

    @Override
    public int updateUserAvatar(Integer userId, String avatar) {
        int i = userInfoExtendMapper.updateUserAvatar(userId, avatar);
        return i;
    }
}
