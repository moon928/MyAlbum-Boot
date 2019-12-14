package cn.yan_wm.myalbum.service.security.oauth2.service.fallback;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.service.security.oauth2.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {
    @Override
    public Account FindAdminByUsername(String username) {
        return null;
    }
}
