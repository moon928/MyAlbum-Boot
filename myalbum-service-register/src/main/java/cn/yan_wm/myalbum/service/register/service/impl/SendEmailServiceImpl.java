package cn.yan_wm.myalbum.service.register.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.register.service.SendEmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @program: MyAlbum-Boot
 * @description: 发送邮件实现〉
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendEmail(SysUser sysUser) {
        try {
            String userJson = MapperUtils.obj2json(sysUser);
            rabbitTemplate.convertAndSend("exchange.direct","registration-code",userJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
