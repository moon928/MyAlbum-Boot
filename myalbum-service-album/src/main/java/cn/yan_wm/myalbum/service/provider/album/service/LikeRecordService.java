package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbLikeRecord;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 点赞Service
 * @author: yan_zt
 * @create: 2020-05-11 01:52
 */
public interface LikeRecordService {

    /**
     * 添加点赞记录
     * @param likeRecord
     * @return
     */
    int add(TbLikeRecord likeRecord);

    /**
     * 查询点赞记录条数
     * @param shareId
     * @param userId
     * @return
     */
    int countLikeRecord(Integer shareId, Integer userId);

    /**
     * 删除点赞记录
     * @param shareId
     * @param userId
     * @return
     */
    int deleteByShareIdAndUserId(Integer shareId, Integer userId);

    boolean isLiked(Integer userId,Integer shareId);

    /**
     * 通过精选id查询点赞记录
     * @param shareId
     * @return
     */
    DataSet<TbLikeRecord> pageLikeRecordByShareId(Integer shareId, Page page);

    /**
     * 通过精选id删除
     * @param shareId
     * @return
     */
    int deleteByShareId(Integer shareId);
}
