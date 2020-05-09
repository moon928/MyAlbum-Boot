package cn.yan_wm.myalbum.service.tools.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.tools.config.FeignConfig;
import cn.yan_wm.myalbum.service.tools.service.fallback.BackstageFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: feign调用backstage的service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = BackstageFeignServiceFallback.class,configuration={FeignConfig.class})
public interface BackstageFeignService {
    @GetMapping("/sysPermission/getPermissionByZuulPrefix")
    public ReturnResult<List<SysPermission>> getSysPermissionByZuulPrefix(@RequestParam("zuulPrefix") String zuulPrefix, @RequestParam("principal") String principal);
}
