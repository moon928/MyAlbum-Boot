package cn.yan_wm.myalbum.service.gateway.config;

import cn.yan_wm.myalbum.service.gateway.filter.AuthorizedRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: MyAlbum-Boot
 * @description: ZuulConfig
 * @author: yan_zt
 * @create: 2020-05-18 18:44
 */
@Configuration
public class ZuulConfig {
    @Bean
    public AuthorizedRequestFilter getAuthorizedRequestFilter() {
        return new AuthorizedRequestFilter() ;
    }
}
