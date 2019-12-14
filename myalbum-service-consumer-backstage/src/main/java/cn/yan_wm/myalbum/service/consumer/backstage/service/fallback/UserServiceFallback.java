package cn.yan_wm.myalbum.service.consumer.backstage.service.fallback;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.service.consumer.backstage.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {

    @Override
    public SysUserExtend findById(Long id) {
        SysUserExtend sysUserExtend = new SysUserExtend();
        sysUserExtend.setId(-1L);
        return sysUserExtend;
    }

    @Override
    public SysUserExtend FindByUsername(String username) {
        return null;
    }

    @Override
    public PageInfo<SysUser> page(int num, int size) {
        return null;
    }
}
