package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.AlbumNumberFeignService;
import org.springframework.stereotype.Component;

/**
 * @program: MyAlbum-Boot
 * @description: AlbumNumberFeign熔断
 * @author: yan_zt
 * @create: 2020-03-29 14:06
 */
@Component
public class AlbumNumberFeignServiceImpl implements AlbumNumberFeignService {
    @Override
    public ReturnResult<Boolean> canAddAlbum(Integer userId, int albumNum) {
        return null;
    }
}
