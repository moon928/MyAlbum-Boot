package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.PrincipalService;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @program: MyAlbum-Boot
 * @description: PrincipalService熔断
 * @author: yan_zt
 * @create: 2020-03-29 16:02
 */
@Component
public class PrincipalFeignServiceImpl implements PrincipalService {

    @Override
    public ReturnResult<SysUser> getUserInfo(String access_token) {
        return null;
    }
}
