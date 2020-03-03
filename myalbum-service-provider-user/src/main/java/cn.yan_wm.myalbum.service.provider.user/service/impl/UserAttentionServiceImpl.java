package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserAttentionExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserAttentionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户关注管理ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class UserAttentionServiceImpl extends BaseServiceImpl<UserAttentionExtend> implements UserAttentionService {
    @Autowired
    private TbUserAttentionExtendMapper tbUserAttentionExtendMapper;

    @Override
    public Mapper<UserAttentionExtend> getMapper() {
        return tbUserAttentionExtendMapper;
    }

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
    public DataSet<UserAttentionExtend> page(Long userId, Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageHelper.orderBy("id DESC");
        PageInfo<UserAttentionExtend> pageInfo = new PageInfo<UserAttentionExtend>(tbUserAttentionExtendMapper.findAll(userId));
        DataSet<UserAttentionExtend> data = super.dataSet(pageInfo);
        return data;
    }

}
