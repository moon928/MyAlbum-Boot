package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.SysAdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "MYALBUM-SERVICE-PROVIDER-BACKSTAGE",fallback = SysAdminServiceFallback.class)
public interface SysAdminService {
    @PostMapping("admin/add")
    public ReturnResult<SysAdmin> add(@RequestBody SysAdmin sysAdmin, @RequestParam("passwprd") String password);

    @GetMapping("admin/unique/{username}")
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username);
}
