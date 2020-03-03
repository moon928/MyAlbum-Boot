package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domain.TbUserResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;
/**
 * @program: MyAlbum-Boot
 * @description: 用户资源数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbUserResourceExtendMapper extends MyMapper<TbUserResource> {
    /**
     * 通过用户id查用户的资源信息
     * @param userId
     * @return
     */
    TbUserResource findByUserId(@Param("userId") Long userId);

    /**
     * 修改vip积分
     * @param userId
     * @param score
     * @return
     */
    int updateVipScoreByUserId(@Param("userId") Long userId,@Param("score") int score);

    /**
     * 修改总图片数量
     * @param userId
     * @param num
     * @return
     */
    int updateImageNum(@Param("userId") Long userId,@Param("num") int num);

    /**
     * 修改粉丝数量
     * @param userId
     * @param num
     * @return
     */
    int updateFanNum(@Param("userId") Long userId,@Param("num") int num);

    /**
     * 修改关注数量
     * @param userId
     * @param num
     * @return
     */
    int updateAttentionNum(@Param("userId") Long userId,@Param("num") int num);
}