package cn.yan_wm.myalbum.service.register.controller;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.validator.BeanValidator;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.register.service.MyPasswordEncoder;
import cn.yan_wm.myalbum.service.register.service.SysAdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("regAdmin")
public class RegisterAdminController extends AbstractBaseController<SysAdmin> {
    @Autowired
    private SysAdminService adminService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @ApiOperation(value = "管理员注册", notes = "以实体类为参数，账号名不要重复")
    @PostMapping(value = "admin")
    public AbstractBaseResult registerAdmin(@ApiParam(name = "admin",value = "管理员莫模型") SysAdmin sysAdmin){
        //数据校验
        String message = BeanValidator.validator(sysAdmin);
        if (StringUtils.isNotBlank(message)){
            return error(message,null);
        }
        //验证用户名是否存在
        if (!adminService.uniqueUsername(sysAdmin.getUsername())){
            return error("用户名 "+sysAdmin.getUsername()+" 已存在",null);
        }
        //注册用户
//        String password = DigestUtils.md5DigestAsHex(sysAdmin.getPassword().getBytes());
        String password = myPasswordEncoder.encode(sysAdmin.getPassword());
        sysAdmin.setStatus(0);
        sysAdmin.setCreateTime(new Date());
        SysAdmin admin = adminService.add(sysAdmin,password);
        if (admin!=null){
            sysAdmin.setId(admin.getId());
            response.setStatus(HttpStatus.CREATED.value());
            return success(request.getRequestURI(), sysAdmin);
        }
        return error("注册失败！请重试",null);
    }
}
