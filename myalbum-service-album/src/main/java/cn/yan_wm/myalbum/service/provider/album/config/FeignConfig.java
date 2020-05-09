package cn.yan_wm.myalbum.service.provider.album.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: MyAlbum-Boot
 * @description: Feign配置类
 * @author: yan_zt
 * @create: 2020-05-07 11:59
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));

        HttpServletRequest request = getServletRequest();
        if (null == request){
            return;
        }

        requestTemplate.header(HttpHeaders.AUTHORIZATION, getHeaders(request));
    }

    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private String getHeaders(HttpServletRequest request){
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
