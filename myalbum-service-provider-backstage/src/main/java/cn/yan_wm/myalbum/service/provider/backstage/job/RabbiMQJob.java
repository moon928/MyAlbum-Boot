package cn.yan_wm.myalbum.service.provider.backstage.job;

import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.provider.backstage.service.BackstageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: MyAlbum-Boot
 * @description: 消息队列job，处理首页访问量添加时的并发问题
 * @author: yan_zt
 * @create: 2020-01-10 16:23
 */
@Service
public class RabbiMQJob {

    @Autowired
    private BackstageService backstageService;

    @RabbitListener(queues = "indexPage-frequency")
    public void addFrequency(String json){
        Map<String,Integer> map;
        try {
            map = MapperUtils.json2pojo(json, Map.class);
            int id =  map.get("indexPageViewId");
            backstageService.updaFrequencyById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
