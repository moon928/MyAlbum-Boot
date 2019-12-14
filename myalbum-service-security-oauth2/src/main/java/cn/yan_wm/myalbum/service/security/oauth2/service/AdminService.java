package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.service.security.oauth2.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MYALBUM-SERVICE-PROVIDER-BACKSTAGE",fallback = AdminServiceFallback.class)
public interface AdminService {
    @GetMapping("account/findAdminByUsername/{username}")
    public Account FindAdminByUsername(@PathVariable("username") String username);
}

