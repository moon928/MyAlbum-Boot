package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.impl.PrincipalFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * @program: MyAlbum-Boot
 * @description: 通过token获取用户信息
 * @author: yan_zt
 * @create: 2020-03-29 16:00
 */
@FeignClient(value = "MYALBUM-SERVICE-SECURITY-OAUTH2", fallback = PrincipalFeignServiceImpl.class)
public interface PrincipalService {
    @GetMapping(value = "/auth/getTokenInfo",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ReturnResult<SysUser> getUserInfo(@RequestParam("access_token") String access_token);
}
