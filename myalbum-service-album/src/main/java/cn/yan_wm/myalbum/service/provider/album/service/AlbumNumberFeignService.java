package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.impl.AlbumNumberFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: MyAlbum-Boot
 * @description: 获取相册数量的feign请求
 * @author: yan_zt
 * @create: 2020-03-29 13:27
 */
@FeignClient(value = "MYALBUM-BACKSTAGE", fallback = AlbumNumberFeignServiceImpl.class)
public interface AlbumNumberFeignService {
    @GetMapping(value = "/vipPermission/canAddAlbum",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Boolean> canAddAlbum(@RequestParam("userId") Integer userId, @RequestParam("albumNum") int albumNum);
}
