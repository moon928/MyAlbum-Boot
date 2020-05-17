package cn.yan_wm.myalbum.service.security.oauth2.controller;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.security.oauth2.service.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Principal user(Principal user) {
        return user;
    }

    @DeleteMapping(value = "exit",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ReturnResult<String> revokeToken(String access_token) {
        //清楚token中的access_token
        if (consumerTokenServices.revokeToken(access_token)) {
            return ReturnResult.success("注销成功");
        } else {
            return ReturnResult.failure("注销失败");
        }
    }

    @GetMapping(value = "/getUserInfo",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取用户信息")
    public Principal getUserInfo(Principal user){
        System.out.println(user);
        return user;
    }
}
