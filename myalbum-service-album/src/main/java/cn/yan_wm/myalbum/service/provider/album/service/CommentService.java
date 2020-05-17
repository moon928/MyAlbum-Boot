package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import cn.yan_wm.myalbum.commons.domainExtend.album.CommentDto;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 评论Service
 * @author: yan_zt
 * @create: 2020-05-10 04:09
 */
public interface CommentService {
    /**
     * 通过分享的稿件id查询评论总数
     * @param shareId
     * @return
     */
    int countCommentByshareId(Integer shareId);

    /**
     * 发表评论
     * @param commentRecord
     * @return
     */
    int sendComment(TbCommentRecord commentRecord);

    /**
     * 通过稿件id分页获取评论信息
     * @param shareId
     * @param page
     * @return
     */
    DataSet<TbCommentRecord> pageCommentByShareId(Integer shareId, Page page);

    /**
     * 删除评论
     * @param id
     * @param userId
     * @return
     */
    int deleteByIdAndUserId(Integer id,Integer userId);

    /**
     * 通过精选id查询评论信息
     * @param shareId
     * @return
     */
    DataSet<TbCommentRecord> listContentByShareId(Integer shareId,Page page);

    /**
     * 通过ShareIds查询所有的评论(分页)
     * @param shareIds
     * @param page
     * @return
     */
    DataSet<TbCommentRecord> pageContentByShareIds(List<Integer> shareIds,Page page);

    /**
     * 通过精选id删除评论
     * @param shareId
     * @return
     */
    int deleteByShareId(Integer shareId);
}
