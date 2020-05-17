package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.domainExtend.album.CommentDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import cn.yan_wm.myalbum.service.provider.album.service.CommentService;
import cn.yan_wm.myalbum.service.provider.album.service.ShareService;
import cn.yan_wm.myalbum.service.provider.album.service.UserInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 评论管理
 * @author: yan_zt
 * @create: 2020-05-10 04:02
 */
@Slf4j
@RestController
@Api(tags = "评论管理")
@RequestMapping(value = "/comment",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class commentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BackstageFeignService backstageFeignService;

    @Autowired
    private ShareService shareService;

    @PostMapping(value = "/sendComment")
    @ApiOperation(value = "发送评论")
    public ReturnResult sendComment(@RequestBody TbCommentRecord commentRecord){
        try{
            if (commentRecord.getIamgeShowId()==null){
                return ReturnResult.failure("稿件 imageShowId 不能为空");
            }
            if (commentRecord.getGetterId()==null){
                return ReturnResult.failure("给受人不能为空");
            }
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                commentRecord.setSenderId(result.getObject().getId());
                commentRecord.setCreateTime(new Date());
                int i = commentService.sendComment(commentRecord);
                if (i>0){
                    return ReturnResult.success("评论成功");
                }else{
                    return ReturnResult.failure("评论失败");
                }
            }else{
                return ReturnResult.failure("BACKSTAGE 服务异常");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("评论失败");
    }

    @ApiOperation(value = "删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true, paramType = "query", dataType = "Long")
    })
    @DeleteMapping(value = "/deleteById")
    public ReturnResult deleteCommentByShareId(Integer id){
        try{
            if (id==null){
                return ReturnResult.failure("评论 id 不能为空");
            }
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                Integer userId = result.getObject().getId();
                int i = commentService.deleteByIdAndUserId(id,userId);
                if (i>0){
                    return ReturnResult.success("删除成功");
                }else{
                    return ReturnResult.failure("删除失败");
                }
            }else{
                return ReturnResult.failure("BACKSTAGE 服务异常");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("删除失败");
    }

    @ApiOperation(value = "通过精选id获取评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享id", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping(value = "/pageContentByShareId")
    public ReturnResult<DataSet<CommentDto>> listContentByShareId(@RequestParam Integer shareId, @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            if (shareId==null){
                return ReturnResult.failure("分享 id 不能为空");
            }
            DataSet<CommentDto> data = new DataSet<CommentDto>();
            List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
            DataSet<TbCommentRecord> dataSet = commentService.listContentByShareId(shareId, page);
            for (TbCommentRecord comment:dataSet.getRows()){
                CommentDto commentDto = new CommentDto();
                UserInfoExtend sender = userInfoService.getUserInfoById(comment.getSenderId());
                commentDto.setSender(sender);
                commentDto.setId(comment.getId());
                commentDto.setIamgeShowId(comment.getIamgeShowId());
                commentDto.setCreateTime(comment.getCreateTime());
                commentDto.setComment(comment.getComment());
                commentDtoList.add(commentDto);
            }
            data.setRows(commentDtoList);
            data.setTotalCount(dataSet.getTotalCount());
            data.setPageSize(dataSet.getPageSize());
            data.setPageNo(dataSet.getPageNo());
            data.setPageCount(dataSet.getPageCount());
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未获取到相应信息");
    }

    @GetMapping("/pageMyComment")
    @ApiOperation(value = "分页获取自己所用的评论消息")
    public ReturnResult<DataSet<CommentDto>> listMyComment(@ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<CommentDto> data = new DataSet<CommentDto>();
            List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                List<TbImageShow> imageShowList = shareService.listShareByUserId(result.getObject().getId());
                List<Integer> imageShowIds = new ArrayList<Integer>();
                for (TbImageShow item: imageShowList){
                    imageShowIds.add(item.getId());
                }
                DataSet<TbCommentRecord> commentRecordDataSet = commentService.pageContentByShareIds(imageShowIds, page);
                for (TbCommentRecord item:commentRecordDataSet.getRows()){
                    CommentDto commentDto = new CommentDto();
                    UserInfoExtend sender = userInfoService.getUserInfoById(item.getSenderId());
                    commentDto.setSender(sender);
                    commentDto.setId(item.getId());
                    commentDto.setIamgeShowId(item.getIamgeShowId());
                    commentDto.setCreateTime(item.getCreateTime());
                    commentDto.setComment(item.getComment());
                    commentDtoList.add(commentDto);
                }
                data.setRows(commentDtoList);
                data.setTotalCount(commentRecordDataSet.getTotalCount());
                data.setPageSize(commentRecordDataSet.getPageSize());
                data.setPageNo(commentRecordDataSet.getPageNo());
                data.setPageCount(commentRecordDataSet.getPageCount());
                return ReturnResult.success(data);
            }else {
                return ReturnResult.failure("BACKSTAGE服务熔断");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

}
