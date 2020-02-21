package cn.yan_wm.myalbum.service.tools.job;

import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @program: MyAlbum-Boot
 * @description: 邮件任务
 * @author: yan_zt
 * @create: 2019-12-20 14:59
 */
@Service
public class EmailJob {
    @Value("${spring.mail.username}")
    private String SPRING_EMAIL_USERNAME;

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = "registration-code")
    public void receive(String  json){
        System.out.println("---------------");
        System.out.println(json);
        System.out.println("------------------");
        Map<String,String> map;
        try {
            map = MapperUtils.json2pojo(json, Map.class);
            sendEmail("欢迎注册", "欢迎 加入MyAlbum大家庭！"+"您的验证码为："+map.get("code"), map.get("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送普通邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendEmail(String subject, String body, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
//        applicationContext.getEnvironment().getProperty("spring.mail.username")
        message.setFrom(SPRING_EMAIL_USERNAME);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    /**
     * 发送 HTML 模板邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendTemplateEmail(String subject, String body, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SPRING_EMAIL_USERNAME);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (Exception e) {

        }
    }
}
