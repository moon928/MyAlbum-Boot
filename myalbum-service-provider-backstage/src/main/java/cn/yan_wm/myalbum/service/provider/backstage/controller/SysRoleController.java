package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 系统角色信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@RestController
@RequestMapping("/sysRole")
@Api(tags = "系统角色信息管理")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;

    @ApiOperation(value = "添加角色信息")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int add(@ApiParam(name = "sysRole",value = "Sys角色Model") SysRole role){
        return roleService.add(role);
    }

    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int deleteById(@PathVariable("id") Long id){
        return roleService.deleteById(id);
    }

    @ApiOperation(value = "修改角色信息")
    @PutMapping(value = "/update" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int update(@ApiParam(name = "sysRole",value = "Sys角色Model") SysRole role){
        return roleService.update(role);
    }

    @ApiOperation(value = "通过id查角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SysRoleExtend getById(@PathVariable("id") Long id){
        return roleService.getById(id);
    }

    @ApiOperation(value = "查所有角色信息")
    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<SysRoleExtend> getAll(){
        return roleService.getAll();
    }
}
