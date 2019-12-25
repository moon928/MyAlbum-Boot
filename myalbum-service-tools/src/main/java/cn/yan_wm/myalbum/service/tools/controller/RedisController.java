package cn.yan_wm.myalbum.service.tools.controller;

import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.tools.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @program: MyAlbum-Boot
 * @description: redisController
 * @author: yan_zt
 * @create: 2019-12-20 10:58
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "redis服务api")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "向redis存值", notes = "向redis存值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key值", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "value", value = "value值", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "seconds", value = "存放时长", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping(value = "/put",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("seconds") Long seconds){
        redisService.put(key,value,seconds);
        return "success";
    }

    @ApiOperation(value = "向redis存值", notes = "向redis存值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key值", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String get(@RequestParam("key") String key) throws Exception {
        Object o = redisService.get(key);
        if (o!=null){
            String obj2json = MapperUtils.obj2json(o);
            return obj2json;
        }
        return "Not_Find";
    }
}
