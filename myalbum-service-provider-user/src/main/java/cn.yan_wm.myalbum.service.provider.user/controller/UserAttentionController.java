package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.user.service.UserAttentionService;
import cn.yan_wm.myalbum.service.provider.user.service.UserFanService;
import cn.yan_wm.myalbum.service.provider.user.service.UserInfoService;
import cn.yan_wm.myalbum.service.provider.user.service.UserResourceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import java.util.Date;


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

    @Autowired
    private UserFanService userFanService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserResourceService userResourceService;

    @ApiOperation(value = "添加关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    })
    @PostMapping("add")
    public ReturnResult addAttention(
            @RequestParam Integer attentionId
    ){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            UserInfoExtend loginUser = userInfoService.getUserInfoByUsername(principal);
            if (loginUser == null){
                return ReturnResult.failure("未找到该登录用户的信息");
            }
            TbUserAttention userAttention = new TbUserAttention();
            userAttention.setUserId(loginUser.getId());
            userAttention.setAttentionId(attentionId);
            userAttention.setCreateTime(new Date());
            int i = userAttentionService.addAttention(userAttention);
            if(i>0){
                //为attention添加粉丝
                int addFan = userFanService.addFan(attentionId, loginUser.getId());
                //自己的关注 +1
                userResourceService.updateAttentionNum(loginUser.getId(),1);
                //attention的粉丝 +1
                userResourceService.updateFanNum(attentionId,1);

                //被关注人分数加2
                userResourceService.updateVipScoreByUserId(attentionId,2);

                return ReturnResult.success("关注成功");

            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("关注失败");
    }

    @ApiOperation(value = "取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
    })
    @DeleteMapping("delete")
    public ReturnResult deleteFan(
            @RequestParam Integer attentionId
    ){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            UserInfoExtend loginUser = userInfoService.getUserInfoByUsername(principal);
            if (loginUser == null){
                return ReturnResult.failure("未找到该登录用户的信息");
            }
            int i = userAttentionService.deleteAttention(loginUser.getId(), attentionId);
            if(i>0){
                //为attention删除粉丝
                userFanService.deleteFan(attentionId, loginUser.getId());
                //自己的关注 -1
                userResourceService.updateAttentionNum(loginUser.getId(),-1);
                //attention的粉丝 -1
                userResourceService.updateFanNum(attentionId,-1);
                return ReturnResult.success("取消关注成功");

            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("取消关注失败");
    }

    @ApiOperation(value = "更新关注备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "note", value = "关注备注", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("update")
    public ReturnResult updateFanNote(
            @RequestParam Integer attentionId,
            @RequestParam String note
    ){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            UserInfoExtend loginUser = userInfoService.getUserInfoByUsername(principal);
            if (loginUser == null){
                return ReturnResult.failure("未找到该登录用户的信息");
            }
            int i = userAttentionService.updateAttentionNote(loginUser.getId(), attentionId, note);
            if(i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

//    @ApiOperation(value = "通过用户id和关注id查单个关注信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "attentionId", value = "关注id", required = true, paramType = "query", dataType = "Long")
//    })
//    @GetMapping("/getAttensionByAttensionId")
//    public ReturnResult<UserAttentionExtend> getFanByFanId(
//            @RequestParam Integer attentionId
//    ){
//        try{
//            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
//            UserInfoExtend loginUser = userInfoService.getUserInfoByUsername(principal);
//            if (loginUser == null){
//                return ReturnResult.failure("未找到该登录用户的信息");
//            }
//            UserAttentionExtend attentionExtend = userAttentionService.getByAttentionId(loginUser.getId(), attentionId);
//            return ReturnResult.success(attentionExtend);
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//        }
//        return ReturnResult.failure("未找到相应数据");
//    }

    @ApiOperation(value = "分页查找关注")
    @GetMapping("/page")
    public ReturnResult<DataSet<UserAttentionExtend>> getFanPage(
            @ApiParam(name = "分页模型") @ModelAttribute Page page
    ){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            UserInfoExtend loginUser = userInfoService.getUserInfoByUsername(principal);
            if (loginUser == null){
                return ReturnResult.failure("未找到该登录用户的信息");
            }
            DataSet data = userAttentionService.page(loginUser.getId(), page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }
}
