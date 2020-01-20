package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.RegisterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "MYALBUM-SERVICE-PROVIDER-BACKSTAGE",fallback = RegisterServiceFallback.class)
public interface RegisterService {
    @GetMapping("sysUser/unique/{username}")
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username);

    @PostMapping("sysUser/add")
    public ReturnResult<SysUser> add(@RequestBody SysUser sysUser,@RequestParam("passwprd") String password);
}
