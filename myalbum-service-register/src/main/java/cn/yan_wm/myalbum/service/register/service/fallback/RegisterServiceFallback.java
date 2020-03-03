package cn.yan_wm.myalbum.service.register.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.RegisterService;
import org.springframework.stereotype.Component;
/**
 * @program: MyAlbum-Boot
 * @description: feign请求  MYALBUM-BACKSTAGE 服务 熔断处理
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
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
