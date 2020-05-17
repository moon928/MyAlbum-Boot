package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.user.service.UserFriendService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

/**
 * @program: MyAlbum-Boot
 * @description: 好友
 * @author: yan_zt
 * @create: 2020-03-02 14:40
 */
@Slf4j
@RestController
@RequestMapping(value = "friend",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "好友管理",hidden = true)
public class UserFriendController {
    @Autowired
    private UserFriendService userFriendService;

    @ApiOperation(value = "添加好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping("add")
    public ReturnResult addFriend(
            @RequestParam Long userId,
            @RequestParam Long friendId
    ){
        try{
            int i = userFriendService.addFriend(userId, friendId);
            if (i>0){
                return ReturnResult.success("添加成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("添加失败");
    }

    @ApiOperation(value = "删除好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long")
    })
    @DeleteMapping("delete")
    public ReturnResult deleteFriend(
            @RequestParam Long userId,
            @RequestParam Long friendId
    ){
        try{
            int i = userFriendService.deleteFriend(userId,friendId);
            if (i>0){
                return ReturnResult.success("删除成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("删除失败");
    }

    @ApiOperation(value = "更新好友备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "备注", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("update")
    public ReturnResult updateFriendNote(
            @RequestParam Long userId,
            @RequestParam Long friendId,
            @RequestParam String note
    ){
        try{
            int i = userFriendService.updateFriendNote(userId,friendId,note);
            if (i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

    @ApiOperation(value = "通过用户id和好友id查单个好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "friendId", value = "好友id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping("getFriendByFriendId")
    public ReturnResult<UserFriendExtend> getFrienByFriendId(
            @RequestParam Long userId,
            @RequestParam Long friendId
    ){
        try{
            UserFriendExtend friendExtend = userFriendService.getByFriendId(userId, friendId);
            return ReturnResult.success(friendExtend);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

    @ApiOperation(value = "分页查找好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping("page")
    public ReturnResult<DataSet<UserFriendExtend>> getFrienPage(
            @RequestParam Long userId,
            @ApiParam(name = "分页模型") @ModelAttribute Page page
    ){
        try{
            DataSet<UserFriendExtend> data = userFriendService.page(userId, page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }
}
