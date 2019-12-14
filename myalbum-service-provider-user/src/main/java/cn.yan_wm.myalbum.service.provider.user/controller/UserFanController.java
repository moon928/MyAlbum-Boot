package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.service.provider.user.service.UserFanService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fan")
public class UserFanController {
    @Autowired
    private UserFanService userFanService;

    @ApiOperation(value = "添加粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @PostMapping("add")
    public int addFan(
            @RequestParam Long userId,
            @RequestParam Long fanId
    ){
        return userFanService.addFan(userId,fanId);
    }

    @ApiOperation(value = "删除粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @DeleteMapping("delete")
    public int deleteFan(
            @RequestParam Long userId,
            @RequestParam Long fanId
    ){
        return userFanService.deleteFan(userId,fanId);
    }

    @ApiOperation(value = "更新粉丝备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "粉丝备注", required = true, paramType = "query", dataType = "String")
    }) //多个请求参数
    @PutMapping("update")
    public int updateFanNote(
            @RequestParam Long userId,
            @RequestParam Long fanId,
            @RequestParam String note
    ){
        return userFanService.updateFanNote(userId,fanId,note);
    }

    @ApiOperation(value = "通过用户id和粉丝id查单个粉丝信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "path", dataType = "Long")
    }) //多个请求参数
    @GetMapping("{userId}/{fanId}")
    public UserFanExtend getFanByFanId(
            @PathVariable Long userId,
            @PathVariable Long fanId
    ){
        UserFanExtend fanExtend = userFanService.getByFanId(userId, fanId);
        return fanExtend;
    }

    @ApiOperation(value = "分页查找粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "path", dataType = "int")
    }) //多个请求参数
    @GetMapping("page/{num}/{size}")
    public PageInfo<UserFriendExtend> getFanPage(
            @RequestParam Long userId,
            @PathVariable int num,
            @PathVariable int size
    ){
        return userFanService.UserFanExtendPage(userId,num,size);
    }
}
