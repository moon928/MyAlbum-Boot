package cn.yan_wm.myalbum.service.tools.controller;

import cn.yan_wm.myalbum.commons.domain.Disk;
import cn.yan_wm.myalbum.commons.domain.Ram;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.tools.server.Server;
import cn.yan_wm.myalbum.service.tools.service.MonitorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("linux")
public class MonitorController extends AbstractBaseController{
    @Autowired
    private MonitorService monitorService;

    @ApiOperation(value = "获取服务器的内存情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping("ram/{id}")
    public AbstractBaseResult getRam(@PathVariable("id") int id){
        Ram ram = new Ram();
        if(id==1){
            ram = monitorService.monitorRam(Server.SERVER01);
        }else if(id==2){
            ram = monitorService.monitorRam(Server.SERVER02);
        }else if(id==3){
            ram = monitorService.monitorRam(Server.SERVER03);
        }
        return success(request.getRequestURI(),ram);
    }

    @ApiOperation(value = "获取文件服务器(FastDFS)的磁盘使用情况")
    @GetMapping("disk")
    public AbstractBaseResult getDisk(){
        Disk disk = new Disk();
        disk = monitorService.monitorDisk(Server.SERVER03);
        return success(request.getRequestURI(),disk);
    }

    @ApiOperation(value = "获取服务器的CPU使用情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务器id", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping("cpu/{id}")
    public AbstractBaseResult getCpu(@PathVariable("id") int id){
        if(id==1){
           monitorService.monitorCpu("1",Server.SERVER01);
        }else if(id==2){
           monitorService.monitorCpu("2",Server.SERVER02);
        }else if(id==3){
            monitorService.monitorCpu("3",Server.SERVER03);
        }
        return success(request.getRequestURI(),"ok");
    }
}
