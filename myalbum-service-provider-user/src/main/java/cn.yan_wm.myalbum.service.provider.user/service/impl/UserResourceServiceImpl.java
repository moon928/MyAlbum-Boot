package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserResource;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserResourceExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
/**
 * @program: MyAlbum-Boot
 * @description: 用户资源信息管理ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(readOnly = true)
public class UserResourceServiceImpl extends BaseServiceImpl<TbUserResource> implements UserResourceService {
    @Autowired
    private TbUserResourceExtendMapper tbUserResourceExtendMapper;

    @Override
    public Mapper<TbUserResource> getMapper() {
        return tbUserResourceExtendMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public int updateVipScoreByUserId(Long userId, int score) {
        return tbUserResourceExtendMapper.updateVipScoreByUserId(userId,score);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateImageNum(Long userId, int num) {
        return tbUserResourceExtendMapper.updateImageNum(userId,num);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateFanNum(Long userId, int num) {
        return tbUserResourceExtendMapper.updateFanNum(userId,num);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateAttentionNum(Long userId, int num) {
        return tbUserResourceExtendMapper.updateAttentionNum(userId,num);
    }
}
