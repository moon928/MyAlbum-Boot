package cn.yan_wm.myalbum.service.consumer.backstage.service;


import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.service.consumer.backstage.service.fallback.UserServiceFallback;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "myalbum-service-provider-backstage",fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("sysUser/findById/{id}")
    public SysUserExtend findById(@PathVariable("id") Long id);

    @GetMapping("sysUser/findByUsername/{username}")
    public SysUserExtend FindByUsername(@PathVariable("username") String username);

    @GetMapping(value = "sysUser/page/{num}/{size}")
    public PageInfo<SysUser> page(
            @PathVariable int num,
            @PathVariable int size
    );
}
