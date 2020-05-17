package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbUserAttentionMapper;
import cn.yan_wm.myalbum.service.provider.album.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 关注管理Service的实现类
 * @author: yan_zt
 * @create: 2020-05-11 09:42
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class AttentionServiceImpl  extends BaseServiceImpl<TbUserAttention> implements AttentionService {

    @Autowired
    private TbUserAttentionMapper userAttentionMapper;

    @Override
    public Mapper<TbUserAttention> getMapper() {
        return userAttentionMapper;
    }


//    @Override
//    public int add(TbUserAttention userAttention) {
//        return 0;
//    }
//
//    @Override
//    public int deleteByUserIdAndAtterntionId(Integer userId, Integer attentionId) {
//        return 0;
//    }

    @Override
    public boolean isAttention(Integer userId, Integer attentionId) {
        TbUserAttention userAttention = new TbUserAttention();
        userAttention.setAttentionId(attentionId);
        userAttention.setUserId(userId);
        List<TbUserAttention> result = userAttentionMapper.select(userAttention);
        if (result !=null && result.size()>0){
            return true;
        }
        return false;
    }
}
