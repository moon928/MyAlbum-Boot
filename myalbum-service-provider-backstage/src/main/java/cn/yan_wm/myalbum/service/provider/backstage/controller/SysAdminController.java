package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminExtendService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminRoleService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @program: MyAlbum-Boot
 * @description: 管理员信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员信息管理")
public class SysAdminController {
    @Autowired
    private SysAdminService adminService;

    @Autowired
    private SysAdminExtendService adminExtendService;

    @Autowired
    private SysAdminRoleService adminRoleService;

    @ApiOperation(value = "校验管理员账号是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "unique/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean uniqueUsername(@PathVariable("username") String username){
        log.info("-----------"+username);
        return adminService.unique("username",username);
    }

    @ApiOperation(value = "添加管理员账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SysAdmin add(@ApiParam(name = "sysAdmin",value = "SysAdmin Model") @RequestBody SysAdmin sysAdmin, @RequestParam("passwprd") String password){
        sysAdmin.setPassword(password);
        SysAdmin admin = (SysAdmin) adminService.save(sysAdmin);
        if (admin != null){
            SysAdminRole adminRole = new SysAdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(10L);
            adminRoleService.insert(adminRole);
            return admin;
        }else {
            return null;
        }
    }

    @ApiOperation(value = "删除管理员账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping(value = "delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int delete(@PathVariable("id") Long id){
        return 0;
    }


    @ApiOperation(value = "分页查寻管理员", notes = "num为页数，size为条数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "page/{num}/{size}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageInfo<SysAdmin> page(
            @PathVariable int num,
            @PathVariable int size
    ){
        return adminExtendService.adminExtendPage(num,size);
    }

    @ApiOperation(value = "根据用户名查管理员账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "findByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SysAdminExtend findByUsername(@PathVariable("username") String username) throws Exception {
        SysAdminExtend adminExtend = adminExtendService.getByUsername(username);
        if(adminExtend == null){
            return null;
        }else{
            return adminExtend;
        }
    }
}
