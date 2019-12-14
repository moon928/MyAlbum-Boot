package cn.yan_wm.myalbum.service.security.oauth2.controller;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class Oauth2Controller extends AbstractBaseController<BaseResult> {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping(value = "user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Principal user(Principal user) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(user);
        return user;
    }

    @DeleteMapping(value = "exit",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public AbstractBaseResult revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return success(request.getRequestURI(),"注销成功");
        } else {
            return error(HttpStatus.UNAUTHORIZED.value(),"注销失败",null);
        }
    }
}
