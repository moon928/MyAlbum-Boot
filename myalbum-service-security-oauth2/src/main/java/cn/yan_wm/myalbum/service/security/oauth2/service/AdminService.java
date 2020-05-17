package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
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
public interface AdminService {

    /**
     * Describe this class
     *
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:06
     */
    Account getByUsername(String username);
}

