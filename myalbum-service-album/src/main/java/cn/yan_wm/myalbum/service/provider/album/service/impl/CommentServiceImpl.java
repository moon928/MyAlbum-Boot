package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.domainExtend.album.CommentDto;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbCommentRecordMapper;
import cn.yan_wm.myalbum.service.provider.album.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.Arrays;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: CommentService实现类
 * @author: yan_zt
 * @create: 2020-05-10 04:11
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class CommentServiceImpl extends BaseServiceImpl<TbCommentRecord> implements CommentService {
    @Autowired
    private TbCommentRecordMapper commentRecordMapper;

    @Override
    public Mapper<TbCommentRecord> getMapper() {
        return commentRecordMapper;
    }

    @Override
    public int countCommentByshareId(Integer shareId) {
        int i = commentRecordMapper.countCommentByshareId(shareId);
        return i;
    }

    @Override
    public int sendComment(TbCommentRecord commentRecord) {
        int i = commentRecordMapper.insertUseGeneratedKeys(commentRecord);
        return i;
    }

    @Override
    public DataSet<TbCommentRecord> pageCommentByShareId(Integer shareId, Page page) {
        Example example = new Example(TbCommentRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("iamgeShowId",shareId);
        List<TbCommentRecord> list = commentRecordMapper.selectByExample(example);
        //分页
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbCommentRecord> pageInfo = new PageInfo<>(list);
        DataSet<TbCommentRecord> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public int deleteByIdAndUserId(Integer id, Integer userId) {
        Example example = new Example(TbCommentRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (id != null){
            criteria.andEqualTo("id",id);
        }else{
            //否则不允许删除
            return 0;
        }
        if (userId != null){
            criteria.andEqualTo("senderId",userId);
        }else{
            return 0;
        }
        return commentRecordMapper.deleteByExample(example);
    }

    @Override
    public DataSet<TbCommentRecord> listContentByShareId(Integer shareId,Page page) {
        Example example = new Example(TbCommentRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("iamgeShowId",shareId);
        List<TbCommentRecord> list = commentRecordMapper.selectByExample(example);

        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbCommentRecord> pageInfo = new PageInfo<>(list);
        DataSet<TbCommentRecord> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public DataSet<TbCommentRecord> pageContentByShareIds(List<Integer> shareIds, Page page) {
        Example example = new Example(TbCommentRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("iamgeShowId", shareIds);
        example.setOrderByClause("create_time DESC");
        List<TbCommentRecord> list = commentRecordMapper.selectByExample(example);

        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbCommentRecord> pageInfo = new PageInfo<>(list);
        DataSet<TbCommentRecord> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public int deleteByShareId(Integer shareId) {
        Example example = new Example(TbCommentRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("iamgeShowId", shareId);
        int i = commentRecordMapper.deleteByExample(example);
        return i;
    }
}
