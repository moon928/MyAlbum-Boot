package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.user.service.UserFanService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

/**
 * @program: MyAlbum-Boot
 * @description: 粉丝
 * @author: yan_zt
 * @create: 2020-03-02 14:40
 */
@Slf4j
@RestController
@RequestMapping(value = "fan",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "粉丝管理")
public class UserFanController {
    @Autowired
    private UserFanService userFanService;

    @ApiOperation(value = "添加粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping("add")
    public ReturnResult addFan(
            @RequestParam Long userId,
            @RequestParam Long fanId
    ){
        try{
            int i = userFanService.addFan(userId, fanId);
            if (i>0){
                return ReturnResult.success("添加成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("添加失败");
    }

    @ApiOperation(value = "删除粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long")
    })
    @DeleteMapping("delete")
    public ReturnResult deleteFan(
            @RequestParam Long userId,
            @RequestParam Long fanId
    ){
        try{
            int i = userFanService.deleteFan(userId,fanId);
            if (i>0){
                return ReturnResult.success("删除成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("删除失败");
    }

    @ApiOperation(value = "更新粉丝备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "粉丝备注", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("update")
    public ReturnResult updateFanNote(
            @RequestParam Long userId,
            @RequestParam Long fanId,
            @RequestParam String note
    ){
        try{
            int i = userFanService.updateFanNote(userId,fanId,note);
            if (i>0){
                return ReturnResult.success("更新成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("更新失败");
    }

    @ApiOperation(value = "通过用户id和粉丝id查单个粉丝信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "fanId", value = "粉丝id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping("getFanByFanId")
    public ReturnResult<UserFanExtend> getFanByFanId(
            @RequestParam Long userId,
            @RequestParam Long fanId
    ){
        try{
            UserFanExtend fanExtend = userFanService.getByFanId(userId, fanId);
            return ReturnResult.success(fanExtend);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

    @ApiOperation(value = "分页查找粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping("/page")
    public ReturnResult<DataSet<UserFanExtend>> getFanPage(
            @RequestParam Long userId,
            @ApiParam(name = "分页模型") @ModelAttribute Page page
    ){
        try{
            DataSet<UserFanExtend> data = userFanService.page(userId, page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }
}
