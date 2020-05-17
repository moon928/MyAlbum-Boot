package cn.yan_wm.myalbum.service.provider.album.mapper;

import cn.yan_wm.myalbum.commons.domain.TbVip;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

/**
 * @program: MyAlbum-Boot
 * @description: vip数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbVipExtendMapper extends MyMapper<TbVip> {
    /**
     * 通过用户id 查询用户的vip信息
     * @param userId
     * @return
     */
    TbVip findByUserId(@Param("userId") Long userId);
}