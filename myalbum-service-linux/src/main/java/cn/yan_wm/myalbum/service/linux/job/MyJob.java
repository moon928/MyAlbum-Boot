package cn.yan_wm.myalbum.service.linux.job;

import cn.yan_wm.myalbum.commons.domain.Cpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Configuration
public class MyJob {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @Scheduled(fixedRate = 1000)
    public void sendMessage(Cpu cpu){
//        System.out.println("来自服务端的消息");
        simpMessagingTemplate.convertAndSend("/server/sendMessageByServer",cpu);

    }
//    @Scheduled(fixedRate = 3000)
    public void sendMessageToUser(){
        System.out.println("来自服务端给指定用户发的消息");
        simpMessagingTemplate.convertAndSendToUser("1","/sendMessageByServer","你好:"+new Date());
    }
}
