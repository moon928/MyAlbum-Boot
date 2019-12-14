package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.service.provider.user.service.UserFriendService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("friend")
public class UserFriendController {
    @Autowired
    private UserFriendService userFriendService;

    @ApiOperation(value = "添加好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @PostMapping("add")
    public int addFriend(
            @RequestParam Long userId,
            @RequestParam Long friendId
    ){
        return userFriendService.addFriend(userId,friendId);
    }

    @ApiOperation(value = "删除好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long")
    }) //多个请求参数
    @DeleteMapping("delete")
    public int deleteFriend(
            @RequestParam Long userId,
            @RequestParam Long friendId
    ){
        return userFriendService.deleteFriend(userId,friendId);
    }

    @ApiOperation(value = "更新好友备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "备注", required = true, paramType = "query", dataType = "String")
    }) //多个请求参数
    @PutMapping("update")
    public int updateFriendNote(
            @RequestParam Long userId,
            @RequestParam Long friendId,
            @RequestParam String note
    ){
        return userFriendService.updateFriendNote(userId,friendId,note);
    }

    @ApiOperation(value = "通过用户id和好友id查单个好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "path", dataType = "Long")
    }) //多个请求参数
    @GetMapping("{userId}/{friendId}")
    public UserFriendExtend getFrienByFriendId(
            @PathVariable Long userId,
            @PathVariable Long friendId
    ){
        UserFriendExtend friendExtend = userFriendService.getByFriendId(userId, friendId);
       return friendExtend;
    }

    @ApiOperation(value = "分页查找好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "path", dataType = "int")
    }) //多个请求参数
    @GetMapping("page/{num}/{size}")
    public PageInfo<UserFriendExtend> getFrienPage(
            @RequestParam Long userId,
            @PathVariable int num,
            @PathVariable int size
    ){
        return userFriendService.UserFriendExtendPage(userId,num,size);
    }
}
