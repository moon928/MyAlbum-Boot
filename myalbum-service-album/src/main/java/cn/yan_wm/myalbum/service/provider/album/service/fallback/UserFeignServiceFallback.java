package cn.yan_wm.myalbum.service.provider.album.service.fallback;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.UserFeignService;
import org.springframework.stereotype.Component;

/**
 * @program: MyAlbum-Boot
 * @description: User服务熔断处理
 * @author: yan_zt
 * @create: 2020-05-07 21:56
 */
@Component
public class UserFeignServiceFallback implements UserFeignService {
    @Override
    public ReturnResult<Integer> relationshipWithOthers(Integer userId, Integer othersId) {
        return null;
    }
}
