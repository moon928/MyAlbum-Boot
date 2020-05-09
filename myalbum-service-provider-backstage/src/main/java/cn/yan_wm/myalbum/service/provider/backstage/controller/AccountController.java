package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: MyAlbum-Boot
 * @description: 用户账号信息
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@RestController
@RequestMapping("/account")
@Api(tags = "用户账号信息")
public class AccountController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysAdminService adminService;


    @ApiOperation(value = "根据用户名查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "findUserByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Account> findUserByUsername(@PathVariable("username") String username){
        SysUserExtend userExtend = userService.getByUsername(username);
        if(userExtend == null){
            return ReturnResult.failure("未找到该用户信息");
        }
        Account account = userAccount(userExtend);
        return ReturnResult.success(account);
    }

    @ApiOperation(value = "根据用户名查管理员账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "findAdminByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Account> findAdminByUsername(@PathVariable("username") String username) {
        SysAdminExtend adminExtend = adminService.getByUsername(username);
        if(adminExtend == null){
            return ReturnResult.failure("未找到该管理员信息");
        }
        Account account = adminAccount(adminExtend);
        return ReturnResult.success(account);
    }

    public Account userAccount(SysUserExtend userExtend){
        Account account = new Account();
        account.setId(userExtend.getId());
        account.setUsername(userExtend.getUsername());
        account.setPassword(userExtend.getPassword());
        account.setStatus(userExtend.getStatus());
        account.setRoleExtends(userExtend.getRoleExtends());
        return account;
    }

    public Account adminAccount(SysAdminExtend adminExtend){
        Account account = new Account();
        account.setId(adminExtend.getId());
        account.setUsername(adminExtend.getUsername());
        account.setPassword(adminExtend.getPassword());
        account.setStatus(adminExtend.getStatus());
        account.setRoleExtends(adminExtend.getRoleExtends());
        return account;
    }
}
