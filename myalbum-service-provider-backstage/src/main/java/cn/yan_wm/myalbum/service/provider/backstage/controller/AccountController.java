package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminExtendService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserExtendService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private SysUserExtendService userExtendService;

    @Autowired
    private SysAdminExtendService adminExtendService;

    @ApiOperation(value = "根据用户名查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "findUserByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Account FindUserByUsername(@PathVariable("username") String username){
        SysUserExtend userExtend = userExtendService.getByUsername(username);
        if(userExtend == null){
            return null;
        }
        return userAccount(userExtend);
    }

    @ApiOperation(value = "根据用户名查管理员账号信息",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping("findAdminByUsername/{username}")
    public Account FindAdminByUsername(@PathVariable("username") String username) {
        SysAdminExtend adminExtend = adminExtendService.getByUsername(username);
        if(adminExtend == null){
            return null;
        }
        return adminAccount(adminExtend);
    }

    public Account userAccount(SysUserExtend userExtend){
        Account account = new Account();
        account.setUsername(userExtend.getUsername());
        account.setPassword(userExtend.getPassword());
        account.setRoleExtends(userExtend.getRoleExtends());
        return account;
    }

    public Account adminAccount(SysAdminExtend adminExtend){
        Account account = new Account();
        account.setUsername(adminExtend.getUsername());
        account.setPassword(adminExtend.getPassword());
        account.setRoleExtends(adminExtend.getRoleExtends());
        return account;
    }
}
