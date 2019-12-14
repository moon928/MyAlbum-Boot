package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.service.provider.user.service.UserAttentionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attention")
public class UserAttentionController {
    @Autowired
    private UserAttentionService userAttentionService;

    @ApiOperation(value = "添加关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @PostMapping("add")
    public int addAttention(
            @RequestParam Long userId,
            @RequestParam Long attentionId
    ){
        return userAttentionService.addAttention(userId,attentionId);
    }

    @ApiOperation(value = "取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @DeleteMapping("delete")
    public int deleteFan(
            @RequestParam Long userId,
            @RequestParam Long attentionId
    ){
        return  userAttentionService.deleteAttention(userId,attentionId);
    }

    @ApiOperation(value = "更新关注备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "关注备注", required = true, paramType = "query", dataType = "String")
    }) //多个请求参数
    @PutMapping("update")
    public int updateFanNote(
            @RequestParam Long userId,
            @RequestParam Long attentionId,
            @RequestParam String note
    ){
        return userAttentionService.updateAttentionNote(userId,attentionId,note);
    }

    @ApiOperation(value = "通过用户id和关注id查单个关注信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "path", dataType = "Long")
    }) //多个请求参数
    @GetMapping("{userId}/{attentionId}")
    public UserAttentionExtend getFanByFanId(
            @PathVariable Long userId,
            @PathVariable Long attentionId
    ){
        UserAttentionExtend attentionExtend = userAttentionService.getByAttentionId(userId, attentionId);
        return attentionExtend;
    }

    @ApiOperation(value = "分页查找关注")
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
        return userAttentionService.UserAttentionExtendPage(userId,num,size);
    }
}
