package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
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

public interface UserService {
    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:10
     */
    Account getByUsername(String username);

}
