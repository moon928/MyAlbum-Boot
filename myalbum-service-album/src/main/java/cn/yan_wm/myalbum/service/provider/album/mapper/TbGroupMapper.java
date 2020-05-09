package cn.yan_wm.myalbum.service.provider.album.mapper;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;


public interface TbGroupMapper extends MyMapper<TbGroup> {

    /**
     * 更新相册
     * @param tbGroup
     * @return
     */
    int update(TbGroup tbGroup);

}