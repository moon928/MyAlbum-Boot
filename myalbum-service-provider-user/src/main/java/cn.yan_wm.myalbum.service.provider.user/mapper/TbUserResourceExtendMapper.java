package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domain.TbUserResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;
public interface TbUserResourceExtendMapper extends MyMapper<TbUserResource> {
    TbUserResource findByUserId(@Param("userId") Long userId);

    //修改vip积分
    int updateVipScoreByUserId(@Param("userId") Long userId,@Param("score") int score);
    //修改总图片数量
    int updateImageNum(@Param("userId") Long userId,@Param("num") int num);
    //修改粉丝数量
    int updateFanNum(@Param("userId") Long userId,@Param("num") int num);
    //修改关注数量
    int updateAttentionNum(@Param("userId") Long userId,@Param("num") int num);
}