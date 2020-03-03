package cn.yan_wm.myalbum.service.security.oauth2.controller;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
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
/**
 * @program: MyAlbum-Boot
 * @description: Oauth2 controller
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@RestController
@RequestMapping("auth")
public class Oauth2Controller {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping(value = "user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Principal user(Principal user) {
        return user;
    }

    @DeleteMapping(value = "exit",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ReturnResult<String> revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return ReturnResult.success("注销成功");
        } else {
            return ReturnResult.failure("注销失败");
        }
    }
}
