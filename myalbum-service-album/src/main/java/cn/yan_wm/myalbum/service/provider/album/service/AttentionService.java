package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;

/**
 * @program: MyAlbum-Boot
 * @description: 关注管理
 * @author: yan_zt
 * @create: 2020-05-11 09:42
 */
public interface AttentionService {

//    /**
//     * 添加关注
//     * @param userAttention
//     * @return
//     */
//    int add(TbUserAttention userAttention);
//
//    /**
//     * 取消关注
//     * @param userId
//     * @param attentionId
//     * @return
//     */
//    int deleteByUserIdAndAtterntionId(Integer userId, Integer attentionId);

    /**
     * 判断是否已关注
     * @param userId
     * @param attentionId
     * @return
     */
    boolean isAttention(Integer userId, Integer attentionId);
}
