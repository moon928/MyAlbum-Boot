package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbUserAttentionExtendMapper extends MyMapper<UserAttentionExtend> {
    int addAttention(@Param("userId") Long userId, @Param("attentionId") Long attentionId);

    int deleteAttention(@Param("userId") Long userId, @Param("attentionId") Long attentionId);

    int updateAttentionNote(@Param("userId") Long userId, @Param("attentionId") Long attentionId, @Param("note") String note);

    UserAttentionExtend findAttentionByAttentionId(@Param("userId") Long userId, @Param("attentionId") Long attentionId);

    List<UserAttentionExtend> findAll(@Param("userId") Long userId);
}