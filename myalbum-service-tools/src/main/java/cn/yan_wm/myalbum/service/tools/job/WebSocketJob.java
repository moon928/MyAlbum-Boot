package cn.yan_wm.myalbum.service.tools.job;

import cn.yan_wm.myalbum.commons.domain.Cpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: MyAlbum-Boot
 * @description: WebSocketJob
 * @author: yan_zt
 * @create: 2019-12-23 10:28
 */
@Configuration
//@Component
public class WebSocketJob {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //定时任务
//    @Scheduled(fixedRate = 1000)
    public void sendMessageByServer(Cpu cpu){
//        System.out.println("===来自服务端发来的消息");
        simpMessagingTemplate.convertAndSend("/server/sendMessageByServer",cpu);
    }

    public void sendCpuInfo(String num,Cpu cpu){
        System.out.println("来自服务端给指定用户发的消息===="+num);
        simpMessagingTemplate.convertAndSendToUser(num,"/sendCpuInfoByServer",cpu);
    }
}
