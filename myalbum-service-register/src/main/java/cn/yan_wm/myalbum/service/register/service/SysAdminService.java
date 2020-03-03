package cn.yan_wm.myalbum.service.register.service;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.register.service.fallback.SysAdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
/**
 * @program: MyAlbum-Boot
 * @description: feign请求 MYALBUM-BACKSTAGE 服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = SysAdminServiceFallback.class)
public interface SysAdminService {
    /**
     * 添加管理员
     * @param sysAdmin
     * @param password
     * @return
     */
    @PostMapping("admin/add")
    public ReturnResult<SysAdmin> add(@RequestBody SysAdmin sysAdmin, @RequestParam("passwprd") String password);

    /**
     * 验证用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("admin/unique/{username}")
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username);
}
