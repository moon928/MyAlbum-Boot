package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.SysAdminService;
import org.springframework.stereotype.Component;
/**
 * @program: MyAlbum-Boot
 * @description: feign请求 MYALBUM-BACKSTAGE 服务 熔断实现
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Component
public class SysAdminServiceFallback implements SysAdminService {
    @Override
    public ReturnResult<SysAdmin> add(SysAdmin sysAdmin, String password) {
        return null;
    }

    @Override
    public ReturnResult<Boolean> uniqueUsername(String username) {
        return null;
    }
}
