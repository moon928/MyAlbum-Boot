package cn.yan_wm.myalbum.service.register.controller;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResult;
import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.commons.validator.BeanValidator;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.register.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping("reg")
public class RegisterController extends AbstractBaseController<SysUser> {
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
    @GetMapping("uniqueUsername/{username}")
    public AbstractBaseResult uniqueUsername(@PathVariable("username") String username){
        Boolean b = registerService.uniqueUsername(username);
        if(b){
            return success(request.getRequestURI(),"ok");
        }
        return error("该邮箱已存在",null);
    }


    @ApiOperation(value = "用户注册", notes = "以实体类为参数，注意用户名和邮箱不要重复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "user")
    public AbstractBaseResult register(@ApiParam(name = "User",value = "用户莫模型") SysUser sysUser, @RequestParam("code") String code) throws Exception {
        String json = redisService.get(sysUser.getUsername());
        if (json.equals("RedisService Blow!")){//redis服务熔断处理 返回503 ，表明服务器暂时处于超负载或正在进行停机维护
            error(HttpStatus.SERVICE_UNAVAILABLE.value(),"RedisService Blow!",null);
        }
        String redisCode = MapperUtils.json2pojo(json,String.class);
        if(!redisCode.equals(code)){
           return error("验证码不正确",null);
        }
        //数据校验
        String message = BeanValidator.validator(sysUser);
        if (StringUtils.isNotBlank(message)){
            return error(message,null);
        }
        //验证用户名是否存在
        if (!registerService.uniqueUsername(sysUser.getUsername())){
            return error("用户名 "+sysUser.getUsername()+" 已存在",null);
        }
        //注册用户

//        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        String password = myPasswordEncoder.encode(sysUser.getPassword());
//        System.out.println(sysUser.getPassword());
//        System.out.println(password);
        sysUser.setStatus(0);
        sysUser.setCreateTime(new Date());
        SysUser user = registerService.add(sysUser,password);
        if (user!=null){
            sysUser.setId(sysUser.getId());
            response.setStatus(HttpStatus.CREATED.value());
            return success(request.getRequestURI(), sysUser);
        }
        return error("注册失败！请重试",null);
    }

}
