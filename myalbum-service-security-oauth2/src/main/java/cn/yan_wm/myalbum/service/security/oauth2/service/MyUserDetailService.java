package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.security.oauth2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
/**
 * @program: MyAlbum-Boot
 * @description: userDetailsService
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ReturnResult<Account> result; //account
        Account account = null;
        //true
        boolean b = username.startsWith("Admin");
        if(b){
            account = adminService.getByUsername(username);
        }else{
            account = userService.getByUsername(username);
        }
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
//        if (result != null && result.isSuccess() && result.getObject() != null){
//            account = result.getObject();
//        }else{
//            throw new UsernameNotFoundException(username);
//        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (SysRoleExtend role : account.getRoleExtends()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
//            for (SysPermission permission : role.getPermissions()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
//                grantedAuthorities.add(authority);
//            }
        }
        User user = new User(account.getId(),account.getUsername(), account.getPassword(),account.getStatus(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
