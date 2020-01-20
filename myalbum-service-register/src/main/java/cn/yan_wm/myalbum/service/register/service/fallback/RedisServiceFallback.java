package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {

    @Override
    public ReturnResult<String> put(String key, String value, Long seconds) {
        return null;
    }

    @Override
    public ReturnResult<String> get(String key) {
        return null;
    }
}
