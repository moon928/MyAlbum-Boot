package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.service.provider.backstage.service.VipPermissionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sysRole/permission")
public class VipPermissionController {
    @Autowired
    private VipPermissionService vipPermissionService;


    @ApiOperation(value = "添加vip限制调条件")
    @PostMapping("add")
    public int add(@ApiParam(name = "tbVipPermission",value = "tbVipPermission Model") TbVipPermission tbVipPermission){
        return vipPermissionService.add(tbVipPermission);
    }

    @ApiOperation(value = "删除vip限制条件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "vipPermission id", required = true, paramType = "path", dataType = "Long")
    }) //多个请求参数
    @DeleteMapping("delete/{id}")
    public int delete(@PathVariable("id") Long id){
       return vipPermissionService.deleteById(id);
    }

    @ApiOperation(value = "更新VIP限制条件")
    @PutMapping("update")
    public int updatePwd(
            @ApiParam(name = "tbVipPermission",value = "tbVipPermission Model")TbVipPermission tbVipPermission
    ){
        return vipPermissionService.update(tbVipPermission);
    }
}
