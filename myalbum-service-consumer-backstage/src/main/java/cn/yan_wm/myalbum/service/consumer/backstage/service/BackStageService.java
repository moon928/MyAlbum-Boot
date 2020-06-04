package cn.yan_wm.myalbum.service.consumer.backstage.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.service.consumer.backstage.service.fallback.BackStageServiceFallback;
import cn.yan_wm.myalbum.service.consumer.backstage.service.fallback.UserServiceFallback;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 后台数据统计Service
 * @author: yan_zt
 * @create: 2020-01-13 09:23
 */
@FeignClient(value = "myalbum-service-provider-backstage",fallback = BackStageServiceFallback.class)
public interface BackStageService {

    @GetMapping("index/getNewUsers")
    public List<BackstageIndexDto> getNewUsers(@RequestParam("type") int type, @RequestParam("month") int month);


    @GetMapping("index/vipDistribution")
    public List<BackstageIndexDto> getVipDistribution();
}
