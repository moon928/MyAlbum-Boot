package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import org.springframework.scheduling.annotation.Async;

public interface SendEmailService {
    @Async
    public void sendEmail(SysUser sysUser);

}
