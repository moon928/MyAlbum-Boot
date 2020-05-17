package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
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
import tk.mybatis.mapper.entity.Example;
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
    public int addAttention(TbUserAttention userAttention) {
        return tbUserAttentionExtendMapper.addAttention(userAttention.getUserId(),userAttention.getAttentionId());
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteAttention(Integer userId, Integer attentionId) {
        return tbUserAttentionExtendMapper.deleteAttention(userId,attentionId);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateAttentionNote(Integer userId, Integer attentionId, String note) {
        return tbUserAttentionExtendMapper.updateAttentionNote(userId,attentionId,note);
    }

    @Override
    public UserAttentionExtend getByAttentionId(Integer userId, Integer attentionId) {
        return tbUserAttentionExtendMapper.findAttentionByAttentionId(userId,attentionId);
    }

    @Override
    public List<UserAttentionExtend> getByUserId(Integer userId) {
        return tbUserAttentionExtendMapper.findAll(userId);
    }

    @Override
    public DataSet<UserAttentionExtend> page(Integer userId, Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageHelper.orderBy("id DESC");
        PageInfo<UserAttentionExtend> pageInfo = new PageInfo<UserAttentionExtend>(tbUserAttentionExtendMapper.findAll(userId));
        DataSet<UserAttentionExtend> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public int getRelationshipWithOthers(Integer userId, Integer othersId) {

        List<TbUserAttention> list = tbUserAttentionExtendMapper.getRelationshipWithOthers(userId, othersId);
        if (list.size()>0){
            //是粉丝
            return 2;
        }
        //路人
        return 0;
    }

}
