package cn.yan_wm.myalbum.service.provider.album.util;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @program: MyAlbum-Boot
 * @description: 登录用户工具类
 * @author: yan_zt
 * @create: 2020-05-07 17:52
 */
public class LoginUserUtile {
    @Autowired
    private BackstageFeignService backstageFeignService;

    public SysUserExtend getLoginUser() throws Exception{
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
        if (result != null && result.isSuccess() && result.getObject() != null){
            return result.getObject();
        }else{
            throw new Exception("BackStage 服务服务异常");
        }
    }
}
