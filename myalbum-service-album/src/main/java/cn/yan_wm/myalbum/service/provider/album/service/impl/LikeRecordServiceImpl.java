package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import cn.yan_wm.myalbum.commons.domain.TbLikeRecord;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbLikeRecordMapper;
import cn.yan_wm.myalbum.service.provider.album.service.LikeRecordService;
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
 * @description: LikeService实现类
 * @author: yan_zt
 * @create: 2020-05-11 01:52
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class LikeRecordServiceImpl extends BaseServiceImpl<TbLikeRecord> implements LikeRecordService {
    @Autowired
    private TbLikeRecordMapper likeRecordMapper;

    @Override
    public Mapper<TbLikeRecord> getMapper() {
        return likeRecordMapper;
    }

    @Override
    public int add(TbLikeRecord likeRecord) {
        //创建
        return likeRecordMapper.insertUseGeneratedKeys(likeRecord);

    }

    @Override
    public int countLikeRecord(Integer shareId, Integer userId) {
        Example example = new Example(TbLikeRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (shareId != null){
            criteria.andEqualTo("imageShowId",shareId);
        }
        if (userId != null){
            criteria.andEqualTo("userId",userId);
        }
        return likeRecordMapper.selectCountByExample(example);
    }

    @Override
    public int deleteByShareIdAndUserId(Integer shareId, Integer userId) {
        Example example = new Example(TbLikeRecord.class);
        Example.Criteria criteria = example.createCriteria();
        if (shareId != null){
            criteria.andEqualTo("imageShowId",shareId);
        }else{
            //不允许删除
            return 0;
        }
        if (userId != null){
            criteria.andEqualTo("userId",userId);
        }else{
            return 0;
        }
        return likeRecordMapper.deleteByExample(example);
    }

    @Override
    public boolean isLiked(Integer userId, Integer shareId) {
        TbLikeRecord likeRecord = new TbLikeRecord();
        likeRecord.setImageShowId(shareId);
        likeRecord.setUserId(userId);
        List<TbLikeRecord> result = likeRecordMapper.select(likeRecord);
        if (result != null && result.size()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public DataSet<TbLikeRecord> pageLikeRecordByShareId(Integer shareId, Page page) {
        Example example = new Example(TbLikeRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("imageShowId",shareId);
        List<TbLikeRecord> list = likeRecordMapper.selectByExample(example);
        //分页
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbLikeRecord> pageInfo = new PageInfo<>(list);
        DataSet<TbLikeRecord> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public int deleteByShareId(Integer shareId) {
        Example example = new Example(TbLikeRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("imageShowId",shareId);
        int i = likeRecordMapper.deleteByExample(example);
        return i;
    }
}
