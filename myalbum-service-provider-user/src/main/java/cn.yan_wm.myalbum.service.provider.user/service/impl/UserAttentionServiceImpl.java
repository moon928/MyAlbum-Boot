package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserAttentionExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserAttentionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserAttentionServiceImpl extends BaseCrudServiceImpl<UserAttentionExtend, TbUserAttentionExtendMapper> implements UserAttentionService<UserAttentionExtend> {
    @Autowired
    private TbUserAttentionExtendMapper tbUserAttentionExtendMapper;

    @Transactional(readOnly = false)
    @Override
    public int addAttention(Long userId, Long attentionId) {
        return tbUserAttentionExtendMapper.addAttention(userId,attentionId);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteAttention(Long userId, Long attentionId) {
        return tbUserAttentionExtendMapper.deleteAttention(userId,attentionId);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateAttentionNote(Long userId, Long attentionId, String note) {
        return tbUserAttentionExtendMapper.updateAttentionNote(userId,attentionId,note);
    }

    @Override
    public UserAttentionExtend getByAttentionId(Long userId, Long attentionId) {
        return tbUserAttentionExtendMapper.findAttentionByAttentionId(userId,attentionId);
    }

    @Override
    public List<UserAttentionExtend> getByUserId(Long userId) {
        return tbUserAttentionExtendMapper.findAll(userId);
    }

    @Override
    public PageInfo<UserAttentionExtend> UserAttentionExtendPage(Long userId ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("id DESC");
        PageInfo<UserAttentionExtend> pageInfo = new PageInfo<>(tbUserAttentionExtendMapper.findAll(userId));
        return pageInfo;
    }
}
