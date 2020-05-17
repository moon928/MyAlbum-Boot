package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.user.service.UserResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @program: MyAlbum-Boot
 * @description: 用户资源信息
 * @author: yan_zt
 * @create: 2020-03-02 14:40
 */
@Slf4j
@RestController
@RequestMapping(value = "resource",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "用户资源信息管理(管理员用)",hidden = true)
public class UserResourceController {
    @Autowired
    private UserResourceService userResourceService;

    @ApiOperation(value = "修改用户vip积分", notes = "userId，score为索要家的积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "score", value = "所加积分", required = true, paramType = "query", dataType = "int")
    })
    @PutMapping("update/vipScore")
    public ReturnResult updateVipScore(
            @RequestParam Integer userId,
            @RequestParam int score)
    {
        try{
            int i = userResourceService.updateVipScoreByUserId(userId, score);
            if (i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

    @ApiOperation(value = "修改用户所有图片数", notes = "userId，num为所加的图片数（可为负数）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "所加数量（可为负数）", required = true, paramType = "query", dataType = "int")
    })
    @PutMapping("update/imageNum")
    public ReturnResult updateImageNum(
            @RequestParam Long userId,
            @RequestParam int num)
    {
        try{
            int i = userResourceService.updateImageNum(userId, num);
            if (i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

    @ApiOperation(value = "修改用户粉丝数量", notes = "userId，num为添加的粉丝数（可为负数）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "所加数量（可为负数）", required = true, paramType = "query", dataType = "int")
    })
    @PutMapping("update/fanNum")
    public ReturnResult updateFanNum(
            @RequestParam Integer userId,
            @RequestParam int num)
    {
        try{
            int i = userResourceService.updateFanNum(userId,num);
            if (i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

    @ApiOperation(value = "修改用户关注数量", notes = "userId，num为添加的关注数（可为负数）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "所加数量（可为负数）", required = true, paramType = "query", dataType = "int")
    })
    @PutMapping("update/attentionNum")
    public ReturnResult updateAttentionNum(
            @RequestParam Integer userId,
            @RequestParam int num)
    {
        try{
            int i = userResourceService.updateAttentionNum(userId,num);
            if (i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }
}
