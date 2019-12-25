package cn.yan_wm.myalbum.service.tools.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;

/**
 * @program: MyAlbum-Boot
 * @description:  redis数据Json格式化
 * @author: yan_zt
 * @create: 2019-12-20 10:56
 */
@Configuration
public class RedisConfig {
    @Autowired
    protected RedisTemplate redisTemplate;

    @PostConstruct
    private void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    }
}
