package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.domainExtend.album.CommentDto;
import cn.yan_wm.myalbum.commons.domainExtend.album.ShareDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.OverallStatisticsDto;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.album.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 公用的Controller，既不需要权限的url
 * @author: yan_zt
 * @create: 2020-05-10 02:39
 */
@Slf4j
@RestController
@Api(tags = "手机端精选页面不用登录的接口")
@RequestMapping(value = "/public",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PubilicController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeRecordService likeRecordService;

    @GetMapping(value = "/pageShare")
    @ApiOperation(value = "获取精选分享",notes = "不传otherId查询全部的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "otherId", value = "他人id",  paramType = "query", dataType = "Long")
    })
    public ReturnResult<DataSet<ShareDto>> pagePublicShare(Integer otherId, @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<ShareDto> resultDto = new DataSet<ShareDto>();
            DataSet<TbImageShow> shareList = new DataSet<TbImageShow>();
            if (otherId == null){
                shareList = shareService.pageShareByUserId(null, page);
            }else{
                shareList = shareService.pageShareByUserId(otherId, page);
            }
            List<ShareDto> data = new ArrayList<ShareDto>();
//            if (shareList.getRows().size() == 0){
//                return ReturnResult.failure("没找到任何分享，你来发一条吧");
//            }
            for (TbImageShow item : shareList.getRows()){
                //获取评论数
                int commentNum = commentService.countCommentByshareId(item.getId());

                ShareDto shareDto = new ShareDto();
                //获取稿件用户信息
                UserInfoExtend userinfo = userInfoService.getUserInfoById(item.getUserId());
                Integer[] imageIds = new Integer[9];
                String[] imageIdStr = item.getImgIds().split(",");
                //将String数组转成Integer数组
                for (int i = 0 ;i<imageIdStr.length ; i++){
                    imageIds[i] = Integer.parseInt(imageIdStr[i]);
                }
                List<TbImage> imageList = imageService.listImageByIds(imageIds);
                if (imageList.size()==0){
                    likeRecordService.deleteByShareId(item.getId());
                    commentService.deleteByShareId(item.getId());
                    shareService.deleteById(item.getId());
                    continue;
                }
                shareDto.setId(item.getId());
                shareDto.setBrowseNum(item.getBrowseNum());
                shareDto.setLikeNum(item.getLikeNum());
                shareDto.setContent(item.getContent());
                shareDto.setVisiblePermissionId(item.getVisiblePermissionId());
                shareDto.setCreateTime(item.getCreateTime());
                shareDto.setCommentNum(commentNum);
                shareDto.setUserInfo(userinfo);
                shareDto.setImageList(imageList);
                data.add(shareDto);
            }
            resultDto.setRows(data);
            resultDto.setTotalCount(shareList.getTotalCount());
            resultDto.setPageSize(shareList.getPageSize());
            resultDto.setPageNo(shareList.getPageNo());
            resultDto.setPageCount(shareList.getPageCount());
            return ReturnResult.success(resultDto);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到响应数据");
    }


    @GetMapping(value = "/pageCommentByShareId")
    @ApiOperation(value = "通过稿件的id获取评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享id", required = true, paramType = "query", dataType = "Long")
    })
    public ReturnResult<DataSet<CommentDto>> pageCommentByShareId(Integer shareId, @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<CommentDto> resultDto = new DataSet<CommentDto>();

            DataSet<TbCommentRecord> pageCommentList = commentService.pageCommentByShareId(shareId, page);

            List<CommentDto> data = new ArrayList<CommentDto>();

            if (pageCommentList.getRows().size() == 0){
                return ReturnResult.failure("未找到任何评论信息，你来发一条吧");
            }
            for (TbCommentRecord item: pageCommentList.getRows()){
                CommentDto commentDto = new CommentDto();
                //获取稿件发表评论用户信息
                UserInfoExtend sender = userInfoService.getUserInfoById(item.getSenderId());

                commentDto.setSender(sender);
                commentDto.setId(item.getId());
                commentDto.setIamgeShowId(item.getIamgeShowId());
                commentDto.setCreateTime(item.getCreateTime());
                commentDto.setComment(item.getComment());

                data.add(commentDto);
            }
            resultDto.setRows(data);
            resultDto.setTotalCount(pageCommentList.getTotalCount());
            resultDto.setPageSize(pageCommentList.getPageSize());
            resultDto.setPageNo(pageCommentList.getPageNo());
            resultDto.setPageCount(pageCommentList.getPageCount());
            return ReturnResult.success(resultDto);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

        return ReturnResult.failure("未找到响应数据");
    }


    @GetMapping(value = "/getOverallStatistics")
    @ApiOperation(value = "获取总体数据统计")
    public ReturnResult<OverallStatisticsDto> getOverallStatistics(){
        try{
            OverallStatisticsDto overallStatisticsDto = new OverallStatisticsDto();
            int countUser = userInfoService.countUser();
            int countImage = imageService.countImage();
            overallStatisticsDto.setUserNum(countUser);
            overallStatisticsDto.setImageNum(countImage);
            return ReturnResult.success(overallStatisticsDto);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("为获取到相应数据");
    }
}
