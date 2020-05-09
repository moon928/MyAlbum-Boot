package cn.yan_wm.myalbum.service.register.controller;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.register.service.RedisService;
import cn.yan_wm.myalbum.service.register.service.SendVerificationCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**
 * @program: MyAlbum-Boot
 * @description:  获取邮箱验证码
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@RestController
@Api(tags = "获取邮箱验证码")
public class EmailCodeController{

    @Autowired
    private SendVerificationCodeService sendVerificationCodeService;

    @Autowired
    private RedisService redisService;


    @ApiOperation(value = "获取邮箱验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户Email", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/emailCode")
    public ReturnResult<String> sendVerificationCode(@RequestParam("email") String email){
        String code = sendVerificationCodeService.getCode(5);
        ReturnResult<String> put;
        put = redisService.put(email, code, 90L);
        if (put.isSuccess() && "ok".equals(put.getObject())){
            sendVerificationCodeService.sendVerificationCode(email,code);
            return ReturnResult.success("ok");
        } else{
            return ReturnResult.failure("向redis存放email_code失败");
        }
    }
}
