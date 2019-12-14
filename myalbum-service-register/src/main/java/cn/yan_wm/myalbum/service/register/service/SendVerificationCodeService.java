package cn.yan_wm.myalbum.service.register.service;

import org.springframework.scheduling.annotation.Async;

/**
 * 发送验证码
 */
public interface SendVerificationCodeService {

    @Async
    public void sendVerificationCode(String email,String code);

    public String getCode(int n);
}
