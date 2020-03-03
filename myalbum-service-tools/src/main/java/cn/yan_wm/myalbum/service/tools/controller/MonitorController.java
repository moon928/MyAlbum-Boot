package cn.yan_wm.myalbum.service.tools.controller;

import cn.yan_wm.myalbum.commons.domain.Disk;
import cn.yan_wm.myalbum.commons.domain.Ram;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.tools.server.Server;
import cn.yan_wm.myalbum.service.tools.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @program: MyAlbum-Boot
 * @description: linux 服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@RestController
@RequestMapping("/linux")
@Api(tags = "linux 服务")
public class MonitorController{
    @Autowired
    private MonitorService monitorService;

    @ApiOperation(value = "获取服务器的内存情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping("/ram/{id}")
    public ReturnResult<Ram> getRam(@PathVariable("id") int id){
        Ram ram = new Ram();
        if(id==1){
            ram = monitorService.monitorRam(Server.SERVER01);
        }else if(id==2){
            ram = monitorService.monitorRam(Server.SERVER02);
        }else if(id==3){
            ram = monitorService.monitorRam(Server.SERVER03);
        }
        return ReturnResult.success(ram);
    }

    @ApiOperation(value = "获取文件服务器(FastDFS)的磁盘使用情况")
    @GetMapping("/disk")
    public ReturnResult<Disk> getDisk(){
        Disk disk = new Disk();
        disk = monitorService.monitorDisk(Server.SERVER03);
        return ReturnResult.success(disk);
    }

    @ApiOperation(value = "获取服务器的CPU使用情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务器id", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping("/cpu/{id}")
    public ReturnResult<String> getCpu(@PathVariable("id") int id){
        if(id==1){
           monitorService.monitorCpu("1",Server.SERVER01);
        }else if(id==2){
           monitorService.monitorCpu("2",Server.SERVER02);
        }else if(id==3){
            monitorService.monitorCpu("3",Server.SERVER03);
        }
        return ReturnResult.success("ok");
    }
}
