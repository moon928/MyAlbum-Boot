package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: url权限管理
 * @author: yan_zt
 * @create: 2020-04-25 11:44
 */
@Slf4j
public class UrlPermissionManage {
    @Autowired
    private BackstageFeignService backstageFeignService;

    public List<SysPermission> listUrlPermission(String zuulPrefix){
        List<SysPermission> data = null;
//        try {
//            ReturnResult<List<SysPermission>> result = backstageFeignService.getSysPermissionByZuulPrefix(zuulPrefix);
//            if (result != null && result.isSuccess()){
//                data = result.getObject();
//            }else {
//                new Exception("BACKSTAGE服务熔断");
//            }
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//        }
        return data;
    }
}
