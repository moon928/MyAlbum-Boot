package cn.yan_wm.myalbum.service.provider.user.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.user.service.BackstageFeignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 后台backstage feign service 熔断处理
 * @author: yan_zt
 * @create: 2020-04-25 11:35
 */
@Component
public class BackstageFeignServiceFallback implements BackstageFeignService {

    @Override
    public ReturnResult<SysUserExtend> findByUsername(String username) {
        return null;
    }

    @Override
    public ReturnResult<Account> findUserByUsername(String username) {
        return null;
    }

    @Override
    public ReturnResult<List<SysPermission>> getSysPermissionByZuulPrefix(String zuulPrefix, String principal) {
        return null;
    }
}
