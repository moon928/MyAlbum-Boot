package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.service.RolePermissionService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysRoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import javax.ws.rs.POST;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 系统角色信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@Slf4j
@RestController
@RequestMapping(value = "/sysRole",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "系统角色信息管理")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation(value = "添加角色信息",notes = "json格式")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> add(@RequestBody SysRole role){
        int i = roleService.add(role);
        if (i>0){
            return ReturnResult.success("添加成功");
        }else{
            return ReturnResult.failure("添加失败");
        }
    }

    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult deleteById(@PathVariable("id") Long id){
        int i = roleService.deleteById(id);
        if (i>0){
            return ReturnResult.success("删除成功");
        }else{
            return ReturnResult.failure("删除失败");
        }
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping(value = "/update" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name="sysRoles",value = "json数组传输",dataType = "SysRole")
    public ReturnResult update(@ApiParam(name = "sysRole",value = "Sys角色Model") @RequestBody List<SysRole> roles){
        int i = 0;
        try{
            for (SysRole role: roles){
                i  += roleService.update(role);
            }
            if (i>0 && i < roles.size()){
                return ReturnResult.success("部分修改成功");
            }else if(i>=roles.size()){
                return ReturnResult.success("修改成功");
            }else{
                return ReturnResult.failure("修改失败");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure("修改失败");
        }
    }

    @ApiOperation(value = "通过id查角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysRoleExtend> getById(@PathVariable("id") Long id){
        SysRoleExtend roleExtend = roleService.getById(id);
        return ReturnResult.success(roleExtend);
    }

    @ApiOperation(value = "查所有角色信息")
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<DataSet<SysRoleExtend>> getAll(@ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<SysRoleExtend> data = roleService.page(page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

    /**
     * Describe this class
     *
     * @param roleId
     * @param permissionIds
     * @exception
     * @return java.lang.Integer[]
     * @author Yzn_zt
     * @date 2020/1/9 17:54
     */

    @PostMapping("/updatePermission")
    @ApiOperation(value = "修改角色权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "Long"),
    })
    public ReturnResult updatePermissionForRole(Long roleId,@RequestParam(value = "permissionIds[]") Long[] permissionIds){
        try{
            int delete = rolePermissionService.deletePermissionByRoleId(roleId);
            int update = rolePermissionService.addpermissionForRole(roleId, permissionIds);
            if (update>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }
}
