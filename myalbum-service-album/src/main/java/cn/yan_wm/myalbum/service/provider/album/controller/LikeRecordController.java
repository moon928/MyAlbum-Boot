package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbLikeRecord;
import cn.yan_wm.myalbum.commons.domainExtend.album.CommentDto;
import cn.yan_wm.myalbum.commons.domainExtend.album.LikeRecordDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import cn.yan_wm.myalbum.service.provider.album.service.LikeRecordService;
import cn.yan_wm.myalbum.service.provider.album.service.UserInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 点赞管理
 * @author: yan_zt
 * @create: 2020-05-11 01:49
 */
@Slf4j
@RestController
@Api(tags = "点赞记录管理")
@RequestMapping("/likeRecord")
public class LikeRecordController {
    @Autowired
    private BackstageFeignService backstageFeignService;

    @Autowired
    private LikeRecordService likeRecordService;

    @Autowired
    private UserInfoService userInfoService;
    @ApiOperation(value = "点赞")
    @PostMapping(value = "clickLike")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享id", required = true, paramType = "query", dataType = "Long")
    })
    public ReturnResult clickLike(@RequestParam("shareId") Integer shareId){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isNotBlank(principal)) {
                TbLikeRecord likeRecord = new TbLikeRecord();

                ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
                if (result != null && result.isSuccess() && result.getObject() != null) {
                    likeRecord.setUserId(result.getObject().getId());
                } else {
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
                likeRecord.setImageShowId(shareId);
                likeRecord.setCreateTime(new Date());
                int i = likeRecordService.add(likeRecord);
                if (i>0){
                    return ReturnResult.success("点赞成功");
                }else{
                    return ReturnResult.failure("点赞失败");
                }
            }else{
                return ReturnResult.failure("未获取到当前登陆用户信息");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("点赞失败");
    }

    @ApiOperation(value = "取消点赞")
    @DeleteMapping(value = "cancelLike")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享id", required = true, paramType = "query", dataType = "Long")
    })
    public ReturnResult cancelLike(Integer shareId){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isNotBlank(principal)) {
                TbLikeRecord likeRecord = new TbLikeRecord();

                ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
                if (result != null && result.isSuccess() && result.getObject() != null) {
                    likeRecord.setUserId(result.getObject().getId());
                } else {
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
                likeRecord.setImageShowId(shareId);
                int count = likeRecordService.countLikeRecord(shareId, result.getObject().getId());
                if (count==0){
                    return ReturnResult.failure("未找到所要删除的记录");
                }
                int i = likeRecordService.deleteByShareIdAndUserId(shareId,result.getObject().getId());
                if (i>0){
                    return ReturnResult.success("取消成功");
                }else{
                    return ReturnResult.failure("取消失败");
                }
            }else{
                return ReturnResult.failure("未获取到当前登陆用户信息");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("取消失败");
    }


    @ApiOperation(value = "通过sahreId获取点赞记录(分页)")
    @PostMapping(value = "/pageLikeRecordByShareId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享id", required = true, paramType = "query", dataType = "Long")
    })
    public ReturnResult getLikeRecord(@RequestParam("shareId") Integer shareId,@ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<LikeRecordDto> data = new DataSet<LikeRecordDto>();
            List<LikeRecordDto> likeRecordDtoList = new ArrayList<LikeRecordDto>();
            DataSet<TbLikeRecord> likeRecordList= likeRecordService.pageLikeRecordByShareId(shareId, page);
            for (TbLikeRecord item:likeRecordList.getRows()){
                LikeRecordDto likeRecordDto = new LikeRecordDto();
                likeRecordDto.setId(item.getId());
                likeRecordDto.setCreateTime(item.getCreateTime());
                likeRecordDto.setImageShowId(item.getImageShowId());
                likeRecordDto.setUser(userInfoService.getUserInfoById(item.getUserId()));
                likeRecordDtoList.add(likeRecordDto);
            }
            data.setRows(likeRecordDtoList);
            data.setTotalCount(likeRecordList.getTotalCount());
            data.setPageSize(likeRecordList.getPageSize());
            data.setPageNo(likeRecordList.getPageNo());
            data.setPageCount(likeRecordList.getPageCount());
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("获取点赞记录失败");
    }
}
