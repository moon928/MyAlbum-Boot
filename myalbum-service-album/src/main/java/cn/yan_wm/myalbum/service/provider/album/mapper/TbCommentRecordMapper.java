package cn.yan_wm.myalbum.service.provider.album.mapper;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface TbCommentRecordMapper extends MyMapper<TbCommentRecord> {
    /**
     * 通过分享的稿件id查询评论总数
     * @param shareId
     * @return
     */
    int countCommentByshareId(@Param("shareId") Integer shareId);
}