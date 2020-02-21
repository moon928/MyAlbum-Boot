package cn.yan_wm.myalbum.service.register.config.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 无效token 异常重写
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint
{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = authException.getCause();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        if(cause instanceof InvalidTokenException) {
            map.put("code", HttpStatus.UNAUTHORIZED.value());//401
            map.put("msg", "无效的token");
        }else{
            map.put("code", HttpStatus.UNAUTHORIZED.value());//401
            map.put("msg", "访问此资源需要完全的身份验证");
        }
        map.put("data", authException.getMessage());
        map.put("success", false);
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(new Date().getTime()));
        try {
            response.getWriter().write(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}