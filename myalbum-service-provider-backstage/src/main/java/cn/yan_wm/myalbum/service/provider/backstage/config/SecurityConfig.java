package cn.yan_wm.myalbum.service.provider.backstage.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

/**
 * @program: MyAlbum-Boot
 * @description: SecurityConfig
 * @author: yan_zt
 * @create: 2020-02-21 14:04
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security_whitelist}")
    private String security_whitelist;
    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info(security_whitelist);
        String[] whitelist = security_whitelist.split(",");
        for (String item : whitelist ){
            System.out.println(item);
            web.ignoring().antMatchers(item);
        }
        //放行swagger-ui
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/actuator/info**",
                "/favicon.ico**");
    }
}
