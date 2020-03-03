package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.user.service.UserAttentionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;


/**
 * @program: MyAlbum-Boot
 * @description: 关注
 * @author: yan_zt
 * @create: 2020-03-02 14:40
 */
@RestController
@RequestMapping(value = "attention",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
@Api(tags = "关注管理")
public class UserAttentionController {
    @Autowired
    private UserAttentionService userAttentionService;

    @ApiOperation(value = "添加关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping("add")
    public ReturnResult addAttention(
            @RequestParam Long userId,
            @RequestParam Long attentionId
    ){
        try{
            int i = userAttentionService.addAttention(userId, attentionId);
            if(i>0){
                return ReturnResult.success("添加成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("添加失败");
    }

    @ApiOperation(value = "取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    })
    @DeleteMapping("delete")
    public ReturnResult deleteFan(
            @RequestParam Long userId,
            @RequestParam Long attentionId
    ){
        try{
            int i = userAttentionService.deleteAttention(userId, attentionId);
            if(i>0){
                return ReturnResult.success("取消成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("取消失败");
    }

    @ApiOperation(value = "更新关注备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "关注备注", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("update")
    public ReturnResult updateFanNote(
            @RequestParam Long userId,
            @RequestParam Long attentionId,
            @RequestParam String note
    ){
        try{
            int i = userAttentionService.updateAttentionNote(userId, attentionId, note);
            if(i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

    @ApiOperation(value = "通过用户id和关注id查单个关注信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping("/getAttensionByAttensionId")
    public ReturnResult<UserAttentionExtend> getFanByFanId(
            @RequestParam Long userId,
            @RequestParam Long attentionId
    ){
        try{
            UserAttentionExtend attentionExtend = userAttentionService.getByAttentionId(userId, attentionId);
            return ReturnResult.success(attentionExtend);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

    @ApiOperation(value = "分页查找关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping("/page")
    public ReturnResult<DataSet<UserAttentionExtend>> getFanPage(
            @RequestParam Long userId,
            @ApiParam(name = "分页模型") @ModelAttribute Page page
    ){
        try{
            DataSet data = userAttentionService.page(userId, page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }
}
