package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "MYALBUM-SERVICE-TOOLS",fallback = RedisServiceFallback.class)
public interface RedisService {
    @PostMapping(value = "redis/put")
    public ReturnResult<String> put(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("seconds") Long seconds);

    @GetMapping(value = "redis/get")
    public ReturnResult<String> get(@RequestParam("key") String key);


}
