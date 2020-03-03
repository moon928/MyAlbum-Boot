package cn.yan_wm.myalbum.service.tools.config;

import cn.yan_wm.myalbum.service.tools.config.handler.AjaxAuthenticationEntryPoint;
import cn.yan_wm.myalbum.service.tools.config.handler.AuthExceptionEntryPoint;
import cn.yan_wm.myalbum.service.tools.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @program: MyAlbum-Boot
 * @description: OAuth资源服务配置
 * @author: yan_zt
 * @create: 2020-03-03 13:57
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
