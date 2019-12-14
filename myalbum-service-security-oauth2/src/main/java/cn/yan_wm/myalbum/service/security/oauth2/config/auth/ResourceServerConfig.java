package cn.yan_wm.myalbum.service.security.oauth2.config.auth;

import cn.yan_wm.myalbum.service.security.oauth2.config.handler.AjaxAuthenticationEntryPoint;
import cn.yan_wm.myalbum.service.security.oauth2.config.handler.AuthExceptionEntryPoint;
import cn.yan_wm.myalbum.service.security.oauth2.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 〈资源认证服务器〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    TokenStore tokenStore;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .and()
                .requestMatchers().antMatchers("/auth/**","/register")
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .and()
                .formLogin()
//                .successHandler(authenticationSuccessHandler) // 登录成功处理
//                .failureHandler(authenticationFailureHandler) // 登录失败处理
                .permitAll()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
        //这里把自定义异常加进去
        resource.tokenStore(tokenStore)
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }
}
