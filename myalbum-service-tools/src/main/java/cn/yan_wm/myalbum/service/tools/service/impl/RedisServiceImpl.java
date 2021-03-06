package cn.yan_wm.myalbum.service.tools.service.impl;

import cn.yan_wm.myalbum.service.tools.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/**
 * @program: MyAlbum-Boot
 * @description: redis 监控ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void put(String key, String value, Long seconds) {
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
