package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.RegisterService;
import org.springframework.stereotype.Component;

@Component
public class RegisterServiceFallback implements RegisterService {

    @Override
    public ReturnResult<Boolean> uniqueUsername(String username) {
        return null;
    }

    @Override
    public ReturnResult<SysUser> add(SysUser sysUser,String password) {
        return null;
    }
}
