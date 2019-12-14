package cn.yan_wm.myalbum.commons.mapper;

import cn.yan_wm.myalbum.commons.domain.TbVip;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface TbVipMapper extends MyMapper<TbVip> {
    TbVip findByUserId(@Param("userId") Long userId);
}