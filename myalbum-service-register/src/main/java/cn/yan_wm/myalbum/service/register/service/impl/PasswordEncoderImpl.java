package cn.yan_wm.myalbum.service.register.service.impl;

import cn.yan_wm.myalbum.service.register.service.MyPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements MyPasswordEncoder {
    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
