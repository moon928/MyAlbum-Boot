package cn.yan_wm.myalbum.service.tools.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.tools.service.BackstageFeignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: BaxxkstageFeign调用熔断
 * @author: yan_zt
 * @create: 2020-05-05 17:38
 */
@Component
public class BackstageFeignServiceFallback implements BackstageFeignService {
    @Override
    public ReturnResult<List<SysPermission>> getSysPermissionByZuulPrefix(String zuulPrefix, String principal) {
        return null;
    }
}
