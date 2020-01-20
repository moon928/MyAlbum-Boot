package cn.yan_wm.myalbum.service.register.controller;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.validator.BeanValidator;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.register.service.MyPasswordEncoder;
import cn.yan_wm.myalbum.service.register.service.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@Slf4j
@RestController
@RequestMapping("/regAdmin")
@Api(tags = "添加管理员")
public class RegisterAdminController{
    @Autowired
    private SysAdminService adminService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    private String adminPrefix = "Admin_";

    @ApiOperation(value = "管理员注册", notes = "以实体类为参数，账号名不要重复")
    @PostMapping(value = "/admin")
    public ReturnResult<SysAdmin> registerAdmin(@ApiParam(name = "admin",value = "管理员莫模型") SysAdmin sysAdmin){
        //数据校验
        String message = BeanValidator.validator(sysAdmin);
        if (StringUtils.isNotBlank(message)){
            return ReturnResult.failure(message);
        }
        String username = adminPrefix+sysAdmin.getUsername();
        //验证用户名是否存在
        ReturnResult<Boolean> returnResult = adminService.uniqueUsername(username);
        if (returnResult.isSuccess() && returnResult.getObject() != null){
            if (!returnResult.getObject()){
                return ReturnResult.failure("用户名 "+sysAdmin.getUsername()+" 已存在");
            }
        }
        //管理员账号前缀默认添加 Admin
        sysAdmin.setUsername(username);
        //注册用户
//        String password = DigestUtils.md5DigestAsHex(sysAdmin.getPassword().getBytes());
        String password = myPasswordEncoder.encode(sysAdmin.getPassword());
        sysAdmin.setStatus(0);
        sysAdmin.setCreateTime(new Date());
        SysAdmin admin = null;
        ReturnResult<SysAdmin> returnResult2 = adminService.add(sysAdmin, password);
        if (returnResult2.isSuccess() && returnResult2.getObject() != null){
            admin = returnResult2.getObject();
        }
        if (admin !=null){
            sysAdmin.setId(admin.getId());
            return ReturnResult.success(sysAdmin);
        }
        return ReturnResult.failure("注册失败！请重试");
    }
}
