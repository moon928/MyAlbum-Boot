package cn.yan_wm.myalbum.service.register.service.impl;

import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.register.service.SendVerificationCodeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SendVerificationCodeServiceImpl implements SendVerificationCodeService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendVerificationCode(String email,String code) {
        Map<String,String> map = new HashMap<>();
        map.put("email",email);
        map.put("code",code);
        try {
            String mapJson = MapperUtils.obj2json(map);
            rabbitTemplate.convertAndSend("exchange.direct","email-code",mapJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCode(int n) {
//        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//保存数字0-9 和 大小写字母
        String string = "0123456789";//保存数字0-9
        char[] ch = new char[n]; //声明一个字符数组对象ch 保存 验证码
        for (int i = 0; i < n; i++) {
            Random random = new Random();//创建一个新的随机数生成器
            int index = random.nextInt(string.length());//返回[0,string.length)范围的int值    作用：保存下标
            ch[i] = string.charAt(index);//charAt() : 返回指定索引处的 char 值   ==》保存到字符数组对象ch里面
        }
        //将char数组类型转换为String类型保存到result
        //String result = new String(ch);//方法一：直接使用构造方法      String(char[] value) ：分配一个新的 String，使其表示字符数组参数中当前包含的字符序列。
        String result = String.valueOf(ch);//方法二： String方法   valueOf(char c) ：返回 char 参数的字符串表示形式。
        return result;
    }
}
