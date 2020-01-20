package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.TbService;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.service.TbServiceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

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
    private TbServiceService serviceListService;

    @ApiOperation(value = "获取服务列表")
    @GetMapping(value = "/page",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<DataSet<TbService>> getService(@ApiParam(name = "分页模型") @ModelAttribute Page page){
        DataSet<TbService> data = serviceListService.page(page);
        return ReturnResult.success(data);
    }

    @ApiOperation(value = "添加服务")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<TbService> add(@ApiParam(name = "tbService",value = "TbService Model") @RequestBody TbService tbService){
        int i = serviceListService.save(tbService);
        if (i > 0){
            return ReturnResult.success();
        }else {
            return ReturnResult.failure();
        }
    }

    @ApiOperation(value = "删除服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务的id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping(value = "/deleteById",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<String> del(@RequestParam("id") Long id){
        int i = serviceListService.deleteById(id);
        if (i > 0 ){
            return ReturnResult.success();
        }else{
            return ReturnResult.failure();
        }
    }
}
