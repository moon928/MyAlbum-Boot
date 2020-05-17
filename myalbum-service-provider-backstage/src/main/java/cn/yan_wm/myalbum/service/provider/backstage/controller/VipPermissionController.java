package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.backstage.service.VipPermissionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RecursiveTask;

/**
 * @program: MyAlbum-Boot
 * @description: 权限信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */
@Slf4j
@RestController
@RequestMapping(value = "/vipPermission",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "权限信息管理")
public class VipPermissionController {
    @Autowired
    private VipPermissionService vipPermissionService;


//    @ApiOperation(value = "添加vip限制调条件")
//    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public int add(@ApiParam(name = "tbVipPermission",value = "tbVipPermission Model") TbVipPermission tbVipPermission){
//        return vipPermissionService.add(tbVipPermission);
//    }
//
//    @ApiOperation(value = "删除vip限制条件")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "vipPermission id", required = true, paramType = "path", dataType = "Long")
//    })
//    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public int delete(@PathVariable("id") Long id){
//       return vipPermissionService.deleteById(id);
//    }


    public ReturnResult<TbVipPermission> listVip(){
        try{

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }


    @ApiOperation(value = "更新VIP限制条件")
    @PutMapping(value = "/update")
    public ReturnResult<String> updatePwd(
            @ApiParam(name = "tbVipPermission",value = "tbVipPermission Model")TbVipPermission tbVipPermission
    ){
        int i = vipPermissionService.update(tbVipPermission);
        if (i>0){
            return ReturnResult.success("更新成功");
        }else {
            return ReturnResult.failure("更新失败");
        }
    }

    @ApiOperation(value = "能否添加相册")
    @GetMapping(value = "/canAddAlbum")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户账号", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "albumNum", value = "已有相册数", required = true, paramType = "query", dataType = "int")
    })
    public ReturnResult<Boolean> canAddAlbum(@RequestParam("userId") Integer userId,@RequestParam("albumNum") int albumNum){
        try{
            Boolean result = vipPermissionService.canAddAlbum(userId, albumNum);
            return ReturnResult.success(result);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure();
    }
}
