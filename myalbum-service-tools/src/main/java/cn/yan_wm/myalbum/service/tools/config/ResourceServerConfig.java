package cn.yan_wm.myalbum.service.tools.config;

import cn.yan_wm.myalbum.service.tools.config.handler.AjaxAuthenticationEntryPoint;
import cn.yan_wm.myalbum.service.tools.config.handler.AuthExceptionEntryPoint;
import cn.yan_wm.myalbum.service.tools.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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
        /*不知道问什么要加上这一句才不报错 ！！！！ */
        http.authorizeRequests().antMatchers("/account/xxxxxx/**").hasAuthority("ROLE");
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(mySecurityMetadataSource());
                        o.setAccessDecisionManager(UrlAccessDecisionManager());
                        return o;
                    }
                });
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
