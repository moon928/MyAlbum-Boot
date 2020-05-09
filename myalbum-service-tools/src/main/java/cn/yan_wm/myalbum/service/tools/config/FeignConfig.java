package cn.yan_wm.myalbum.service.tools.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
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

        requestTemplate.header("access_token", new String[] {getHeaders(request)});
    }

    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private String getHeaders(HttpServletRequest request){
        return request.getHeader("access_token");
    }
}
