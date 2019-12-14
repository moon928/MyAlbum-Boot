package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.service.register.service.RegisterService;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFallback implements RegisterService {

    @Override
    public Boolean uniqueUsername(String username) {
        return false;
    }

    @Override
    public SysUser add(SysUser sysUser,String password) {
        return null;
    }
}
