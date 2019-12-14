package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;

    @ApiOperation(value = "添加角色信息")
    @PostMapping("add")
    public int add(@ApiParam(name = "sysRole",value = "Sys角色Model") SysRole role){
        return roleService.add(role);
    }

    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping("delete/{id}")
    public int deleteById(@PathVariable("id") Long id){
        return roleService.deleteById(id);
    }

    @ApiOperation(value = "修改角色信息")
    @PutMapping("update")
    public int update(@ApiParam(name = "sysRole",value = "Sys角色Model") SysRole role){
        return roleService.update(role);
    }

    @ApiOperation(value = "通过id查角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping("{id}")
    public SysRoleExtend getById(@PathVariable("id") Long id){
        return roleService.getById(id);
    }

    @ApiOperation(value = "差所有角色信息")
    @GetMapping()
    public List<SysRoleExtend> getAll(){
        return roleService.getAll();
    }
}
