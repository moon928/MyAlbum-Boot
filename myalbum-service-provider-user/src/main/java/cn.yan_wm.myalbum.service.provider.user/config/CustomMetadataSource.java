package cn.yan_wm.myalbum.service.provider.user.config;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.user.service.BackstageFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 *  匹配访问资源
 */
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private BackstageFeignService backstageFeignService;

    @Value("${myalbum.zuulPerfix}")
    private String zuulPrefix;

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //获取登陆的用户名
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReturnResult<List<SysPermission>> returnResult = backstageFeignService.getSysPermissionByZuulPrefix(zuulPrefix, (String) principal);
        if (returnResult != null && returnResult.getObject().size()>0){
            for (SysPermission menu : returnResult.getObject()) {
                if (antPathMatcher.match("/"+menu.getServicePrefix()+"/"+menu.getUri(), requestUrl)) {
                    return SecurityConfig.createList(menu.getUri());
                }
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
