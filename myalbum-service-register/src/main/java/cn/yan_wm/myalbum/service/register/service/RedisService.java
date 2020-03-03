package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @program: MyAlbum-Boot
 * @description:  feign请求 MYALBUM-SERVICE-TOOLS服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@FeignClient(value = "MYALBUM-SERVICE-TOOLS",fallback = RedisServiceFallback.class)
public interface RedisService {
    /**
     * redis 存放
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @PostMapping(value = "redis/put")
    public ReturnResult<String> put(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("seconds") Long seconds);

    /**
     * redis 获取
     * @param key
     * @return
     */
    @GetMapping(value = "redis/get")
    public ReturnResult<String> get(@RequestParam("key") String key);


}
