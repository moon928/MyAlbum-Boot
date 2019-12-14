package cn.yan_wm.myalbum.service.redis.service;

public interface RedisService {
    /**
     *
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    public void put(String key, String value, Long seconds);

    public Object get(String key);
}
