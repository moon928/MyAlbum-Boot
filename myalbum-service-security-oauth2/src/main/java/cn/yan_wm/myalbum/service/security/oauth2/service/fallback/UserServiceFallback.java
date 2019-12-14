package cn.yan_wm.myalbum.service.security.oauth2.service.fallback;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.service.security.oauth2.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {
    @Override
    public Account FindUserByUsername(String username) {
        return null;
    }
}
