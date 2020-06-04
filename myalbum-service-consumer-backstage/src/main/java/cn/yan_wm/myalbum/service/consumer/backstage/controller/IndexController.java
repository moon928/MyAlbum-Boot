package cn.yan_wm.myalbum.service.consumer.backstage.controller;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.consumer.backstage.service.BackStageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 数据统计服务Controller
 * @author: yan_zt
 * @create: 2020-01-13 09:22
 */
@Slf4j
@RestController
@Api(tags = "后台-数据统计")
public class IndexController extends AbstractBaseController {

    @Autowired
    private BackStageService backStageService;

    @GetMapping("/getNewUsers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "周-1，月-2", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "查前n个月(type=2时必填)",required = false, paramType = "query", dataType = "int")
    })
    public AbstractBaseResult getNewUsers(@RequestParam("type") int type, @RequestParam("month") int month){
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        try{
            if (type == 2 && StringUtils.isEmpty(month+"")){
                return error("当type==2时，moth不能为空","IndexController---getNewUsers()---当type==2时，moth不能为空");
            }
            list = backStageService.getNewUsers(type, month);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return success(request.getRequestURI(),list);
    }

    @GetMapping("/vipDistribution")
    @ApiOperation("获取vip等级分布")
    public AbstractBaseResult getVipDistribution(){
        return null;
    }
}
