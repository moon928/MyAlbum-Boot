package cn.yan_wm.myalbum.service.provider.backstage.service;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

public interface SysUserService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    int updatePwd(Long userId,String password);

    int updateStatus(Long userId,int status);

    int updateUsername(Long userId,String username);
}
