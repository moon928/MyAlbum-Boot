package cn.yan_wm.myalbum.service.security.oauth2.service;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.service.security.oauth2.config.auth.RedisTokenStore;
import cn.yan_wm.myalbum.service.security.oauth2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * @program: MyAlbum-Boot
 * @description: token服务
 * @author: yan_zt
 * @create: 2020-03-29 16:58
 */
@Service
@Configuration
public class TokenService {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    public SysUser getUserInfoByToken(String access_token){
        OAuth2Authentication oAuth2Authentication = tokenStore().readAuthentication(access_token);
        User user = (User) oAuth2Authentication.getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setStatus(user.getStstus());
        sysUser.setUsername(user.getUsername());
        return sysUser;
    }
}
