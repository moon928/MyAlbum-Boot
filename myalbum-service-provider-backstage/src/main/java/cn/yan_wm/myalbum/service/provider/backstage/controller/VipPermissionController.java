package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.service.provider.backstage.service.VipPermissionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @program: MyAlbum-Boot
 * @description: 权限信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@RestController
@RequestMapping("/sysRole/permission")
@Api(tags = "权限信息管理")
public class VipPermissionController {
    @Autowired
    private VipPermissionService vipPermissionService;


    @ApiOperation(value = "添加vip限制调条件")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int add(@ApiParam(name = "tbVipPermission",value = "tbVipPermission Model") TbVipPermission tbVipPermission){
        return vipPermissionService.add(tbVipPermission);
    }

    @ApiOperation(value = "删除vip限制条件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "vipPermission id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int delete(@PathVariable("id") Long id){
       return vipPermissionService.deleteById(id);
    }

    @ApiOperation(value = "更新VIP限制条件")
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int updatePwd(
            @ApiParam(name = "tbVipPermission",value = "tbVipPermission Model")TbVipPermission tbVipPermission
    ){
        return vipPermissionService.update(tbVipPermission);
    }
}
