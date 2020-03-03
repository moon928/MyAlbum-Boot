package cn.yan_wm.myalbum.service.register.service.impl;

import cn.yan_wm.myalbum.service.register.service.MyPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
/**
 * @program: MyAlbum-Boot
 * @description:  密码加密实现
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
public class PasswordEncoderImpl implements MyPasswordEncoder {
    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
