package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.domain.SysUserRole;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserService;
import cn.yan_wm.myalbum.service.provider.backstage.service.UserRoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

/**
 * @program: MyAlbum-Boot
 * @description: 用户信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api(tags = "用户信息管理")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserRoleService userRoleService;


    @ApiOperation(value = "判断用户名（邮箱）是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username(Email)", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/unique/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username){
        boolean b = false;
        try{
            b = sysUserService.unique("username", username);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure("/sysUser/unique/{username} 异常");
        }
        return ReturnResult.success(b);
    }

    @ApiOperation(value = "添加用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "新的密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysUser> add(@ApiParam(name = "sysUser",value = "Sys用户Model") @RequestBody SysUser sysUser,@RequestParam("passwprd") String password){
        sysUser.setPassword(password);
        SysUser user = (SysUser) sysUserService.save(sysUser);
        if(user!=null){
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(1L);
            userRoleService.insert(user.getId(),1L);
            return ReturnResult.success(user);
        }else{
            return ReturnResult.failure();
        }
    }

/*    @ApiOperation(value = "删除用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping("delete/{id}")
    public int delete(@PathVariable("id") Long id){
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return sysUserService.delete(sysUser);
    }*/

    @ApiOperation(value = "更新用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "password", value = "新的密码", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping(value = "/updatePwd",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> updatePwd(
            @RequestParam Long id,
            @RequestParam String password
    ){
        int i = sysUserService.updatePwd(id, password);
        if (i>0){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @ApiOperation(value = "更新用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态（0：正常 1：锁定 -1：逻辑删除）", required = true, paramType = "query", dataType = "int")
    })
    @PutMapping(value = "/updateStatus",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> updateStatus(
            @RequestParam Long id,
            @RequestParam int status
    ){
        int i = sysUserService.updateStatus(id, status);
        if (i>0){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @ApiOperation(value = "更新用户绑定的邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "username", value = "新的用户名（邮箱）", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping(value = "/updateUsername",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> updateUsername(
            @RequestParam Long id,
            @RequestParam String username
    ){
        int i = sysUserService.updateUsername(id, username);
        if (i>0){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @ApiOperation(value = "根据id查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/findById/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysUserExtend> findById(@PathVariable("id") Long id) {
        SysUserExtend sysUserExtend = sysUserService.getById(id);
        return ReturnResult.success(sysUserExtend);
    }

    @ApiOperation(value = "根据用户名查用户账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/findByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysUserExtend> findByUsername(@PathVariable("username") String username) {
        SysUserExtend userExtend = sysUserService.getByUsername(username);
        return ReturnResult.success(userExtend);
    }

    @ApiOperation(value = "分页查用户账号信息")
    @GetMapping(value = "/page",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<DataSet<SysUserExtend>> page(@ApiParam(name = "分页模型") @ModelAttribute Page page){
        DataSet<SysUserExtend> data = sysUserService.page(page);
        return ReturnResult.success(data);
    }

    @ApiOperation(value = "为用户授予角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping(value = "/authorization",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> authorization(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId
    ){
        int i = userRoleService.insert(userId,roleId);
        if (i>0){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @ApiOperation(value = "删除用户授予的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long")
    })
    @DeleteMapping(value = "deleteAuthorization",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> deleteAuthorization(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId
    ){
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        int i = userRoleService.deleteByUserIdAndRoleId(userId,roleId);
        if (i>0){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @ApiOperation(value = "更新用户授予的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long")
    })
    @PutMapping(value = "/updateAuthorization",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> updateAuthorization(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId
    ){
        //先删除之前的角色
        int resule = userRoleService.deleteByUserId(userId);
        //再添加新的角色
        if(resule>0){
            int i = userRoleService.insert(userId,roleId);
            if (i>0){
                return ReturnResult.success();
            }
            return ReturnResult.failure();
        }else {
            return ReturnResult.failure();
        }
    }
}
