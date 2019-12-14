package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.service.register.service.SysAdminService;
import org.springframework.stereotype.Component;

@Component
public class SysAdminServiceFallback implements SysAdminService {
    @Override
    public SysAdmin add(SysAdmin sysAdmin, String password) {
        return null;
    }

    @Override
    public Boolean uniqueUsername(String username) {
        return null;
    }
}
