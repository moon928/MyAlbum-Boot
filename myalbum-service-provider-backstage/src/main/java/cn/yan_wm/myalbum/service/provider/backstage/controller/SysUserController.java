package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserExtendService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserService;
import cn.yan_wm.myalbum.service.provider.backstage.service.UserRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SysUserExtendService userExtendService;

    @ApiOperation(value = "判断用户名（邮箱）是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username(Email)", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping("unique/{username}")
    public Boolean uniqueUsername(@PathVariable("username") String username){
        return sysUserService.unique("username",username);
    }

    @ApiOperation(value = "添加用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "新的密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("add")
    public SysUser add(@ApiParam(name = "sysUser",value = "Sys用户Model") @RequestBody SysUser sysUser,@RequestParam("passwprd") String password){
        sysUser.setPassword(password);
        SysUser user = (SysUser) sysUserService.save(sysUser);
        if(user!=null){
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(1L);
            userRoleService.insert(userRole);
            return user;
        }else{
            return null;
        }
    }

//    @ApiOperation(value = "删除用户账号信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
//    }) //多个请求参数
//    @DeleteMapping("delete/{id}")
//    public int delete(@PathVariable("id") Long id){
//        SysUser sysUser = new SysUser();
//        sysUser.setId(id);
//        return sysUserService.delete(sysUser);
//    }

    @ApiOperation(value = "更新用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "password", value = "新的密码", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("updatePwd")
    public int updatePwd(
            @RequestParam Long id,
            @RequestParam String password
    ){
        return sysUserService.updatePwd(id,password);
    }

    @ApiOperation(value = "更新用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态（0：正常 1：锁定 -1：逻辑删除）", required = true, paramType = "query", dataType = "int")
    }) //
    @PutMapping("updateStatus")
    public int updateStatus(
            @RequestParam Long id,
            @RequestParam int status
    ){
        return sysUserService.updateStatus(id,status);
    }

    @ApiOperation(value = "更新用户绑定的邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "username", value = "新的用户名（邮箱）", required = true, paramType = "query", dataType = "String")
    }) //
    @PutMapping("updateUsername")
    public int updateUsername(
            @RequestParam Long id,
            @RequestParam String username
    ){
        return sysUserService.updateUsername(id,username);
    }

    @ApiOperation(value = "根据id查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("findById/{id}")
    public SysUserExtend findById(@PathVariable("id") Long id) {
        SysUserExtend sysUserExtend = userExtendService.getById(id);
        return sysUserExtend;
    }

    @ApiOperation(value = "根据用户名查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping("findByUsername/{username}")
    public SysUserExtend FindByUsername(@PathVariable("username") String username) {
        SysUserExtend userExtend = userExtendService.getByUsername(username);
        if(userExtend == null){
            return null;
        }else{
            return userExtend;
        }
    }

    @ApiOperation(value = "分页查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "path", dataType = "int")
    }) //多个请求参数
    @GetMapping(value = "page/{num}/{size}")
    public PageInfo<SysUser> page(
            @PathVariable int num,
            @PathVariable int size
    ){
        return userExtendService.sysUserExtendPage(num,size);
    }

    @ApiOperation(value = "为用户授予角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @PostMapping("authorization")
    public int authorization(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId
    ){
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRoleService.insert(userRole);
    }

    @ApiOperation(value = "删除用户授予的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @DeleteMapping("deleteAuthorization")
    public int deleteAuthorization(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId
    ){
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRoleService.delete(userRole);
    }

    @ApiOperation(value = "更新用户授予的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @PutMapping("updateAuthorization")
    public int updateAuthorization(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId
    ){
        //先删除之前的角色
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        int resule = userRoleService.delete(userRole);
        //再添加新的角色
        if(resule>0){
            userRole.setRoleId(roleId);
            return userRoleService.insert(userRole);
        }else {
            return 0;
        }

    }
}
