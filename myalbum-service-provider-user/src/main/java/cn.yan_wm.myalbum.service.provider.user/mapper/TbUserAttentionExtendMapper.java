package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 用户关注数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbUserAttentionExtendMapper extends MyMapper<UserAttentionExtend> {
    /**
     * 添加关注人
     * @param userId
     * @param attentionId
     * @return
     */
    int addAttention(@Param("userId") Integer userId, @Param("attentionId") Integer attentionId);

    /**
     * 取消关注
     * @param userId
     * @param attentionId
     * @return
     */
    int deleteAttention(@Param("userId") Integer userId, @Param("attentionId") Integer attentionId);

    /**
     * 修改关注人备注
     * @param userId
     * @param attentionId
     * @param note
     * @return
     */
    int updateAttentionNote(@Param("userId") Integer userId, @Param("attentionId") Integer attentionId, @Param("note") String note);

    /**
     * 通过关注者id查询关注人信息
     * @param userId
     * @param attentionId
     * @return
     */
    UserAttentionExtend findAttentionByAttentionId(@Param("userId") Integer userId, @Param("attentionId") Integer attentionId);

    /**
     * 通过用户id查询所有的关注人
     * @param userId
     * @return
     */
    List<UserAttentionExtend> findAll(@Param("userId") Integer userId);

    /**
     * 判断是否是粉丝（是否是我关注的）
     * @param userId
     * @param othersId
     * @return
     */
    List<TbUserAttention> getRelationshipWithOthers(@Param("userId") Integer userId, @Param("othersId") Integer othersId);
}