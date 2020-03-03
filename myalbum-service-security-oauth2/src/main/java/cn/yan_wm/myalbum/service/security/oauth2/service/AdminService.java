package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.security.oauth2.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * @program: MyAlbum-Boot
 * @description: feign 请求MYALBUM-BACKSTAGE 服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = AdminServiceFallback.class)
public interface AdminService {

    /**
     * feign 通过用户名获取管理员信息
     * @param username
     * @return
     */
    @GetMapping(value = "account/findAdminByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Account> findAdminByUsername(@PathVariable("username") String username);
}

