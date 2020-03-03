package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.RegisterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
/**
 * @program: MyAlbum-Boot
 * @description: feign请求  MYALBUM-BACKSTAGE 服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = RegisterServiceFallback.class)
public interface RegisterService {
    /**
     * 验证用户名是否存在
     * @param username
     * @return
     */
    @GetMapping(value = "sysUser/unique/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username);

    /**
     * 添加用户
     * @param sysUser
     * @param password
     * @return
     */
    @PostMapping(value = "sysUser/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysUser> add(@RequestBody SysUser sysUser,@RequestParam("passwprd") String password);
}
