package cn.yan_wm.myalbum.service.provider.backstage.config;

import cn.yan_wm.myalbum.service.provider.backstage.config.handler.AjaxAuthenticationEntryPoint;
import cn.yan_wm.myalbum.service.provider.backstage.config.handler.AuthExceptionEntryPoint;
import cn.yan_wm.myalbum.service.provider.backstage.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 〈OAuth资源服务配置〉
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/service/page/**").hasAuthority("page");
        http.authorizeRequests().antMatchers("/sysRole/all").hasAuthority("ROLE");

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .and()
                .httpBasic();
   }
    @Override
    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
        //这里把自定义异常加进去
        resource
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }



}
