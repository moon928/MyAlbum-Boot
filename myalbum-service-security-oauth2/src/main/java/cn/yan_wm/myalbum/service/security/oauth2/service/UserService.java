package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.security.oauth2.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * @program: MyAlbum-Boot
 * @description: feign请求 MYALBUM-BACKSTAGE 服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */

@FeignClient(value = "MYALBUM-BACKSTAGE",fallback = UserServiceFallback.class)
public interface UserService {
    /**
     * feign 请求 通过用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "account/findUserByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Account> findUserByUsername(@PathVariable("username") String username);
}
