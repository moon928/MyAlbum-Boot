package cn.yan_wm.myalbum.service.redis.controller;

import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping(value = "put")
    public String put(String key, String value, Long seconds){
        redisService.put(key,value,seconds);
        return "success";
    }

    @GetMapping(value = "get")
    public String get(String key) throws Exception {
        Object o = redisService.get(key);
        if (o!=null){
            String obj2json = MapperUtils.obj2json(o);
            return obj2json;
        }
        return "Not_Find";
    }
}
