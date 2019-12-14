package cn.yan_wm.myalbum.service.register.controller;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.register.service.RedisService;
import cn.yan_wm.myalbum.service.register.service.SendVerificationCodeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("emailCode")
public class EmailCodeController extends AbstractBaseController<BaseResult> {

    @Autowired
    private SendVerificationCodeService sendVerificationCodeService;

    @Autowired
    private RedisService redisService;


    @ApiOperation(value = "获取邮箱验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户Email", required = true, paramType = "query", dataType = "String")
    }) //多个请求参数
    @GetMapping("")
    public AbstractBaseResult SendVerificationCode(@RequestParam("email") String email){
        String code = sendVerificationCodeService.getCode(5);
        String put = redisService.put(email, code, 100L);
        if (put.equals("success")){
            sendVerificationCodeService.sendVerificationCode(email,code);
            return success(request.getRequestURI(),"ok");
        } else{
            return error("请重新发送",null);
        }
    }
}
