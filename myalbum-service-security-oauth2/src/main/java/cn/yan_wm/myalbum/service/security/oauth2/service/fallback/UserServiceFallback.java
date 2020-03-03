package cn.yan_wm.myalbum.service.security.oauth2.service.fallback;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.security.oauth2.service.UserService;
import org.springframework.stereotype.Component;
/**
 * @program: MyAlbum-Boot
 * @description: feign请求 MYALBUM-BACKSTAGE 服务 熔断处理
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Component
public class UserServiceFallback implements UserService {

    @Override
    public ReturnResult<Account> findUserByUsername(String username) {
        return null;
    }
}
