package cn.yan_wm.myalbum.service.register.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @program: MyAlbum-Boot
 * @description: 发送验证码〉
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SendVerificationCodeService {

    /**
     * 发送验证码
     * @param email
     * @param code
     */
    @Async
    public void sendVerificationCode(String email,String code);

    /**
     * 获取验证码
     * @param n
     * @return
     */
    public String getCode(int n);
}
