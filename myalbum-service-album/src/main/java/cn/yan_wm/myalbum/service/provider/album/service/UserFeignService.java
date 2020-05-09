package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.fallback.UserFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: MyAlbum-Boot
 * @description: User服务feign调用
 * @author: yan_zt
 * @create: 2020-05-07 21:53
 */
@FeignClient(value = "MYALBUM-USER",fallback = UserFeignServiceFallback.class)
public interface UserFeignService {
    /**
     * 查看自己与他人关系
     * @param userId
     * @param othersId
     * @return
     */
    @GetMapping(value = "/user/relationshipWithOthers")
    public ReturnResult<Integer> relationshipWithOthers(@RequestParam("userId") Integer userId, @RequestParam("othersId") Integer othersId);
}