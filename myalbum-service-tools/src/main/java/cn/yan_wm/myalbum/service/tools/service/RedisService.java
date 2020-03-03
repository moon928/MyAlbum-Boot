package cn.yan_wm.myalbum.service.tools.service;
/**
 * @program: MyAlbum-Boot
 * @description: redis 监控Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface RedisService {
    /**
     * redis存放
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    public void put(String key, String value, Long seconds);

    /**
     * redis 取出
     * @param key
     * @return
     */
    public Object get(String key);
}
