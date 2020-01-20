package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.IndexPageView;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.utils.DateUtils;
import cn.yan_wm.myalbum.service.provider.backstage.service.BackstageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 后台首页的Controller
 * @author: yan_zt
 * @create: 2020-01-10 10:39
 */
@Slf4j
@RestController
@Api(tags = "后台-数据统计")
@RequestMapping(value = "/index",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
public class IndexController {

    @Autowired
    private BackstageService backstageService;

    @GetMapping("/getNewUsers")
    @ApiOperation("获取新增用户量(周/月)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "周-1，月-2", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "查前n个月(type=2时必填)",required = false, paramType = "query", dataType = "int")
    })
    public ReturnResult<List<BackstageIndexDto>> getNewUsers(@RequestParam("type") int type, @RequestParam("month") int month){
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        try{
            if (type == 1){
                list = backstageService.getNewUsersAWeek();
            }else if (type == 2){
                if (!StringUtils.isEmpty(month+"")){
                    list = backstageService.getNewUsersMonth(month);
                }else {
                    log.error("type==2时，month不允许为空");
                    return ReturnResult.failure("type==2时，month不允许为空");
                }
            }else{
                log.error("type的类型不正确 周-1，月-2");
                return ReturnResult.failure("type的类型不正确 周-1，月-2");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.success(list);
    }

    @GetMapping("/vipDistribution")
    @ApiOperation("获取vip等级分布")
    public ReturnResult<List<BackstageIndexDto>> getVipDistribution(){
        List<BackstageIndexDto> vipDistribution = new ArrayList<BackstageIndexDto>();
        try{
            vipDistribution = backstageService.getVipDistribution();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.success(vipDistribution);
    }


    @PostMapping("/addFrequency")
    @ApiOperation("添加访问次数")
    public ReturnResult addFrequency(){
        try {
            DateUtils dateUtils = new DateUtils();
            String nyr = dateUtils.getday("yyyy-MM-dd",0);
            List<IndexPageView> list = backstageService.getFrequencyByTime(nyr);
            if (list != null && list.size()>0){
                Long id = list.get(0).getId();
                backstageService.sendIndexPageViewIdToMPQ(id);
                return ReturnResult.success();
            }else{
                int i = backstageService.addFrequency();
                if (i>0){
                    return ReturnResult.success();
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure();
        }
        return ReturnResult.failure();
    }


    @GetMapping("/getFrequency")
    @ApiOperation("查询访问量(周/月)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "周-1，月-2", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "month", value = "查前n个月(type=2时必填)",required = false, paramType = "query", dataType = "int")
    })
    public ReturnResult<List<BackstageIndexDto>> getIndexPageView(@RequestParam("type") int type, @RequestParam("month") int month){
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        try{
            if (type == 1){
                list = backstageService.getIndexPageViewsAWeek();
            }else if (type == 2){
                if (!StringUtils.isEmpty(month+"")){
                    list = backstageService.getIndexPageViewsMonth(month);
                }else {
                    log.error("type==2时，month不允许为空");
                    return null;
                }
            }else{
                log.error("type的类型不正确 周-1，月-2");
                return null;
            }
            return ReturnResult.success(list);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure();
    }
}
