package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.service.register.service.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {

    @Override
    public String put(String key, String value, Long seconds) {
        return "RedisService Blow!";
    }

    @Override
    public String get(String key) {
        return "RedisService Blow!";
    }
}
