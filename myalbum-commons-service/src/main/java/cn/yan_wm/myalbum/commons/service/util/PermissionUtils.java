package cn.yan_wm.myalbum.commons.service.util;

import cn.yan_wm.myalbum.commons.domain.SysPermission;

/**
 * @program: MyAlbum-Boot
 * @description: 权限管理工具类
 * @author: yan_zt
 * @create: 2020-04-23 20:00
 */
public class PermissionUtils {
    /**
     * 将权限转换为url
     * @param permission
     * @return
     */
    public String permissionToUrl(SysPermission permission){
        String url = "";
        url += permission.getServicePrefix()+"/"+permission.getUri();
        return null;
    }
}
