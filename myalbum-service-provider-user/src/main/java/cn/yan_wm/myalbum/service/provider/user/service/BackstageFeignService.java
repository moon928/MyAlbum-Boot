package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.user.service.fallback.BackstageFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 通用的Service
 * @author: yan_zt
 * @create: 2020-05-05 21:13
 */
@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = BackstageFeignServiceFallback.class)
public interface BackstageFeignService {

    @GetMapping(value = "/sysUser/findByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysUserExtend> findByUsername(@PathVariable("username") String username);

    /**
     * feign 请求 通过用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "account/findUserByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Account> findUserByUsername(@PathVariable("username") String username);

    @GetMapping("/sysPermission/getPermissionByZuulPrefix")
    public ReturnResult<List<SysPermission>> getSysPermissionByZuulPrefix(@RequestParam("zuulPrefix") String zuulPrefix, @RequestParam("principal") String principal);

}
