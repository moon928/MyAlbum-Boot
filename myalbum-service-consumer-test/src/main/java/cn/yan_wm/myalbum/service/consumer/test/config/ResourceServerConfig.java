package cn.yan_wm.myalbum.service.consumer.test.config;

import cn.yan_wm.myalbum.service.consumer.test.config.handler.AjaxAuthenticationEntryPoint;
import cn.yan_wm.myalbum.service.consumer.test.config.handler.AuthExceptionEntryPoint;
import cn.yan_wm.myalbum.service.consumer.test.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

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
        http.authorizeRequests().antMatchers("/test/page/**").hasAuthority("page");
        http.authorizeRequests().antMatchers("/test/hello").hasAuthority("hello");

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
