package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.service.provider.user.service.UserResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("resource")
public class UserResourceController {
    @Autowired
    private UserResourceService userResourceService;

    @ApiOperation(value = "修改用户vip积分", notes = "userId，score为索要家的积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "score", value = "所加积分", required = true, paramType = "query", dataType = "int")
    }) //多个请求参数
    @PutMapping("update/vipScore")
    public int updateVipScore(
            @RequestParam Long userId,
            @RequestParam int score)
    {
        return userResourceService.updateVipScoreByUserId(userId,score);
    }

    @ApiOperation(value = "修改用户所有图片数", notes = "userId，num为所加的图片数（可为负数）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "所加数量（可为负数）", required = true, paramType = "query", dataType = "int")
    }) //多个请求参数
    @PutMapping("update/imageNum")
    public int updateImageNum(
            @RequestParam Long userId,
            @RequestParam int num)
    {
        return userResourceService.updateImageNum(userId,num);
    }

    @ApiOperation(value = "修改用户粉丝数量", notes = "userId，num为添加的粉丝数（可为负数）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "所加数量（可为负数）", required = true, paramType = "query", dataType = "int")
    }) //多个请求参数
    @PutMapping("update/fanNum")
    public int updateFanNum(
            @RequestParam Long userId,
            @RequestParam int num)
    {
        return userResourceService.updateFanNum(userId,num);
    }

    @ApiOperation(value = "修改用户关注数量", notes = "userId，num为添加的关注数（可为负数）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "所加数量（可为负数）", required = true, paramType = "query", dataType = "int")
    }) //多个请求参数
    @PutMapping("update/attentionNum")
    public int updateAttentionNum(
            @RequestParam Long userId,
            @RequestParam int num)
    {
        return userResourceService.updateAttentionNum(userId,num);
    }
}
