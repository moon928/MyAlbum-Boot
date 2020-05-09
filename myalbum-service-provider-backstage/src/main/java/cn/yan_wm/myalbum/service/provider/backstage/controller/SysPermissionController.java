package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysPermissionExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.backstage.service.RolePermissionService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysPermissionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @program: MyAlbum-Boot
 * @description: 角色权限Contoller
 * @author: yan_zt
 * @create: 2020-01-09 14:27
 */
@Slf4j
@RestController
@RequestMapping(value = "/sysPermission",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "系统权限管理")
public class SysPermissionController {
    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/save")
    @ApiOperation(value = "添加/修改SysPermission")
    public ReturnResult<String> add(@ApiParam(name = "sysPermission",value = "SysPermission Model") SysPermission permission){
        SysPermission save = (SysPermission) permissionService.save(permission);
        if (save != null){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除SysPermission")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "权限id",required = true,paramType = "path", dataType = "Long")
    })
    public ReturnResult<String> deleteById(@PathVariable("id") Integer id){
        int deleteNum = permissionService.deleteById(id);
        if (deleteNum>0){
            return ReturnResult.success();
        }
        return ReturnResult.failure();
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取劝权限树")
    public ReturnResult<List<SysPermissionExtend>> getSysPermissionTree(){
        try {
            List<SysPermissionExtend> permissionTree = permissionService.getSysPermissionTree();
            return ReturnResult.success(permissionTree);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure();
    }

    /**
     * Describe this class
     *
     * @param roleId
     * @exception
     * @return java.lang.Integer[]
     * @author Yzn_zt
     * @date 2020/1/9 17:21
     */
    @GetMapping("getPermissionByRoleId/{roleId}")
    @ApiOperation(value = "通过角色id查找该角色的所有权限id（Integer[]）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "权限id",required = true,paramType = "path", dataType = "Long")
    })
    public ReturnResult<Integer[]> getRolePermissionByRoleId(@PathVariable("roleId") Long roleId){
        try{
            Integer[] permissionIds = rolePermissionService.getPermissionIdsByRoleId(roleId);
            return ReturnResult.success(permissionIds);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure();
    }

    @GetMapping("/getPermissionByZuulPrefix")
    @ApiOperation(value = "通过网关名获取权限列表--后台专用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zuulPrefix", value = "网关前缀名",required = true,paramType = "query", dataType = "String")
    })
    public ReturnResult<List<SysPermission>> getSysPermissionByZuulPrefix(@RequestParam("zuulPrefix") String zuulPrefix,@RequestParam("principal") String principal){
        try{
            List<SysPermission> permissionList = permissionService.getSysPermissionByZuulPrefix(zuulPrefix,principal);
            return ReturnResult.success(permissionList);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure();
    }

}
