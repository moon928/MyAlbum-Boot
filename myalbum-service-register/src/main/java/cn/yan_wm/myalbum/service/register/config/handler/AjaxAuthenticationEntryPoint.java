package cn.yan_wm.myalbum.service.register.config.handler;

import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @program: MyAlbum-Boot
 * @description:  未登录处理
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Component
public class AjaxAuthenticationEntryPoint extends AbstractBaseController<BaseResult> implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().write(JSON.toJSONString(error("尚未登陆，请先登录",null)));
    }

}
