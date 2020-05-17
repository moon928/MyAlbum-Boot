package cn.yan_wm.myalbum.service.provider.backstage.config;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.service.util.PermissionUtils;
import cn.yan_wm.myalbum.service.provider.backstage.config.handler.AjaxAuthenticationEntryPoint;
import cn.yan_wm.myalbum.service.provider.backstage.config.handler.AuthExceptionEntryPoint;
import cn.yan_wm.myalbum.service.provider.backstage.config.handler.CustomAccessDeniedHandler;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: OAuth资源服务配置
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean("FilterInvocationSecurityMetadataSource")
    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        CustomMetadataSource securityMetadataSource = new CustomMetadataSource();
        return securityMetadataSource;
    }
    @Bean("AccessDecisionManager")
    public AccessDecisionManager UrlAccessDecisionManager() {
        return new UrlAccessDecisionManager();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 通过获取配置文件中的服务名称 zuul_prefix 且 service_prefix 不为 null 来查该服务的所有菜单列表
         * 并拼接为 service_prefix/uri
         * 其中关联查询角色表，并将角色表的role_name 去掉 前缀 ROLE_
         */

        /*不知道问什么要加上这一句才不报错 ！！！！ */

        http
                //// 禁用csrf
                .csrf().disable()
                .exceptionHandling()
                //未登录处理，规定返回给前端未登陆的错误码数据格式
                .authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                //权限不足处理，规定返回给前端权限不足错误码数据格式
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .httpBasic();
        http.authorizeRequests().antMatchers("/account/xxxxxx/**").hasAuthority("ROLE");
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        //动态规划各请求路径所需要的权限
                        o.setSecurityMetadataSource(mySecurityMetadataSource());
                        //对各个权限角色的处理
                        o.setAccessDecisionManager(UrlAccessDecisionManager());
                        return o;
                    }
                });
   }
    @Override
    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
        //这里把自定义异常加进去
        resource
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

}
