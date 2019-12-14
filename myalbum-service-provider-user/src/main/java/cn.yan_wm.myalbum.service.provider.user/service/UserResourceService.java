package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import org.apache.ibatis.annotations.Param;

public interface UserResourceService<T extends AbstractBaseDomain> extends BaseCrudService<T>{
    int updateVipScoreByUserId(Long userId,int score);

    int updateImageNum(Long userId,int num);

    int updateFanNum(Long userId,int num);

    int updateAttentionNum(Long userId,int num);

}
