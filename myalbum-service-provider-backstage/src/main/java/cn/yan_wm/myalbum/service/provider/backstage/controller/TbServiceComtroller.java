package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domain.TbService;
import cn.yan_wm.myalbum.service.provider.backstage.service.ServiceListService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 获取服务器列表
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@RestController
@RequestMapping("/service")
@Api(tags = "获取服务器列表")
public class TbServiceComtroller {
    @Autowired
    private ServiceListService serviceListService;

    @ApiOperation(value = "获取服务列表")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TbService> getService(){
        List<TbService> serviceLists = serviceListService.selectAll();
        return serviceLists;
    }

    @ApiOperation(value = "添加服务")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TbService add(@ApiParam(name = "tbService",value = "TbService Model") @RequestBody TbService tbService){
        TbService service = (TbService) serviceListService.save(tbService);
        if (service != null){
            return service;
        }else {
            return null;
        }
    }

    @ApiOperation(value = "删除服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务的id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping(value = "/deleteById",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean del(@RequestParam("id") Long id){
        TbService tbService = new TbService();
        tbService.setId(id);
        int i = serviceListService.delete(tbService);
        if (i == 0 ){
            return false;
        }else{
            return true;
        }
    }
}
