package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserResource;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbUserResourceExtendMapper;
import cn.yan_wm.myalbum.service.provider.album.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: MyAlbum-Boot
 * @description: 用户资源信息管理ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UserResourceServiceImpl extends BaseServiceImpl<TbUserResource> implements UserResourceService {
    @Autowired
    private TbUserResourceExtendMapper tbUserResourceExtendMapper;

    @Override
    public Mapper<TbUserResource> getMapper() {
        return tbUserResourceExtendMapper;
    }

    @Override
    public int updateVipScoreByUserId(Integer userId, int score) {
        return tbUserResourceExtendMapper.updateVipScoreByUserId(userId,score);
    }

    @Override
    public int updateImageNum(Integer userId, int num) {
        return tbUserResourceExtendMapper.updateImageNum(userId,num);
    }

}
