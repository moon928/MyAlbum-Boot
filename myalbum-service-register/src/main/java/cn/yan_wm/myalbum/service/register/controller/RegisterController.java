package cn.yan_wm.myalbum.service.register.controller;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.commons.validator.BeanValidator;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.register.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
@Slf4j
@RestController
@RequestMapping("/reg")
@Api(tags = "注册用户")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @ApiOperation(value = "用户注册", notes = "以实体类为参数，注意用户名和邮箱不要重复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/uniqueUsername/{username}",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username){
        ReturnResult<Boolean> returnResult = registerService.uniqueUsername(username);
        if(returnResult!= null && returnResult.isSuccess() && returnResult.getObject() != null){
            return ReturnResult.success(returnResult.getObject());
        }else {
            return ReturnResult.failure("PROVIDER-BACKSTAGE 服务异常");
        }
    }


    @ApiOperation(value = "用户注册", notes = "以实体类为参数，注意用户名和邮箱不要重复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "user",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public ReturnResult<SysUser> register(@ApiParam(name = "User",value = "用户莫模型") SysUser sysUser, @RequestParam("code") String code) throws Exception {
        ReturnResult<String> returnResult = redisService.get(sysUser.getUsername());
        try{
            if (returnResult != null){
                String json = returnResult.getObject();
                if (StringUtils.isEmpty(json)){
                    return ReturnResult.failure("验证码不正确");
                }
                String redisCode = MapperUtils.json2pojo(json,String.class);
                if(!redisCode.equals(code)){
                    return ReturnResult.failure("验证码不正确");
                }
                //数据校验
                String message = BeanValidator.validator(sysUser);
                if (StringUtils.isNotBlank(message)){
                    return ReturnResult.failure(message);
                }
                //验证用户名是否存在
                ReturnResult<Boolean> returnResult1 = registerService.uniqueUsername(sysUser.getUsername());
                if (returnResult1 != null && returnResult1.isSuccess() && returnResult1.getObject() != null){
                    if (!returnResult1.getObject()){
                        return ReturnResult.failure("用户名 "+sysUser.getUsername()+" 已存在");
                    }
                }
                //注册用户
//        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                String password = myPasswordEncoder.encode(sysUser.getPassword());
//        System.out.println(sysUser.getPassword());
//        System.out.println(password);
                sysUser.setStatus(0);
                sysUser.setCreateTime(new Date());
                ReturnResult<SysUser> add = registerService.add(sysUser, password);
                SysUser user = add.getObject();
                if (user!=null){
                    sysUser.setId(sysUser.getId());
                    return ReturnResult.success(sysUser);
                }
            }else {
                return ReturnResult.failure("Service-Tools 服务异常");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure("注册失败！请重试");
        }
        return ReturnResult.failure("注册失败！请重试");
    }

}
