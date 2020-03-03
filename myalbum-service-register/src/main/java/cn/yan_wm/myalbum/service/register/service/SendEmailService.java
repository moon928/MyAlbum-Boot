package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import org.springframework.scheduling.annotation.Async;
/**
 * @program: MyAlbum-Boot
 * @description: 发送邮件〉
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface SendEmailService {
    /**
     * 发送邮件
     * @param sysUser
     */
    @Async
    public void sendEmail(SysUser sysUser);

}
