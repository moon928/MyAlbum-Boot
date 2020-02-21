package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.RegisterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = RegisterServiceFallback.class)
public interface RegisterService {
    @GetMapping(value = "sysUser/unique/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username);

    @PostMapping(value = "sysUser/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysUser> add(@RequestBody SysUser sysUser,@RequestParam("passwprd") String password);
}
