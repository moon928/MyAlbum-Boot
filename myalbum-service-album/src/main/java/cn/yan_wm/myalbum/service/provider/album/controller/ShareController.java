package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.domainExtend.album.ShareDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.album.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import javax.ws.rs.DELETE;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 分享管理Controller
 * @author: yan_zt
 * @create: 2020-05-10 01:31
 */
@Slf4j
@RestController
@Api(tags = "分享管理")
@RequestMapping(value = "/share",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShareController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private BackstageFeignService backstageFeignService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private LikeRecordService likeRecordService;

    @Autowired
    private AttentionService attentionService;

    @ApiOperation(value = "保存分享（添加时不传id）",notes = "json格式")
    @PostMapping(value = "/save")
    public ReturnResult saveShare(@RequestBody TbImageShow imageShow){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isNotBlank(principal)){
                ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
                if (result != null && result.isSuccess() && result.getObject() != null){
                    imageShow.setUserId(result.getObject().getId());
                }else{
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
                if (imageShow.getVisiblePermissionId()==null){
                    //全部人可见 默认
                    imageShow.setVisiblePermissionId(3);
                }
                imageShow.setLikeNum(0);
                imageShow.setCreateTime(new Date());
                imageShow.setBrowseNum(0);
                if (StringUtils.isBlank(imageShow.getImgIds()) && StringUtils.isBlank(imageShow.getContent())){
                    return ReturnResult.failure("没有图片总得说点什么吧 0.0");
                }
                TbImageShow data = shareService.saveShare(imageShow);
                if (data.getId()!=null){
                    return ReturnResult.success("分享成功");
                }else{
                    return ReturnResult.failure("分享失败");
                }
            }else{
                return ReturnResult.failure("未获取到登陆用户信息");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("f分享失败");
    }


    @ApiOperation(value = "通过id删除分享")
    @DeleteMapping(value = "deleteShare")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享id", required = true, paramType = "query", dataType = "Long")
    })
    public ReturnResult deleteShareById(@RequestParam("shareId") Integer shareId){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isNotBlank(principal)) {
                ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
                if (result != null && result.isSuccess() && result.getObject() != null) {
                    TbImageShow share = shareService.getShareById(shareId);
                    if (share != null) {
                        if (share.getUserId() != result.getObject().getId()){
                            return ReturnResult.failure("对不起，自己只能删除自己的分享哦");
                        }else{
                            likeRecordService.deleteByShareId(shareId);
                            commentService.deleteByShareId(shareId);
                            int i = shareService.deleteById(shareId);
                            if (i>0){
                                return ReturnResult.success("删除成功");
                            }else{
                                return ReturnResult.failure("删除失败");
                            }
                        }
                    }else{
                        return ReturnResult.failure("未查询到 id 为"+shareId+"的信息");
                    }
                } else {
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
            }else{
                return ReturnResult.failure("未获取到登陆用户信息");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("删除失败");
    }


    @GetMapping(value = "/pageShare")
    @ApiOperation(value = "获取精选分享(带是否点赞与关注，需要登陆)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mark", value = "标记（0- 全部,1-查看自己的 2-查看他人）", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "otherId", value = "他人Id", paramType = "query", dataType = "Long")
    })
    public ReturnResult<DataSet<ShareDto>> pagePublicShare(@ApiParam(name = "分页模型") @ModelAttribute Page page,@RequestParam Integer mark, Integer otherId){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isNotBlank(principal)) {
                ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
                if (result != null && result.isSuccess() && result.getObject() != null) {
                    DataSet<ShareDto> resultDto = new DataSet<ShareDto>();
//                    List<Integer> permissionIds = new ArrayList<Integer>(8);
                    DataSet<TbImageShow> shareList = new DataSet<TbImageShow>();
//                    permissionIds.add(3);
                    if (mark == 0) {
                        shareList = shareService.pageShareByUserId(null, page);
                    }else if (mark == 1){
//                        permissionIds.add(1);
//                        permissionIds.add(2);
                        shareList = shareService.pageShareByUserId(result.getObject().getId(), page);
                    }else if (mark == 2){
//                        permissionIds.add(2);
                        shareList = shareService.pageShareByUserId(otherId, page);
                    }else{
                        return ReturnResult.failure("mark 标志不正确");
                    }
                    List<ShareDto> data = new ArrayList<ShareDto>();
//                    if (shareList.getRows().size() == 0){
//                        return ReturnResult.failure("没找到任何分享");
//                    }
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
                        shareDto.setIsLiked(likeRecordService.isLiked( result.getObject().getId(),item.getId()));
                        shareDto.setIsAttention(attentionService.isAttention(result.getObject().getId(),item.getUserId()));
                        data.add(shareDto);
                }
                    resultDto.setRows(data);
                    resultDto.setTotalCount(shareList.getTotalCount());
                    resultDto.setPageSize(shareList.getPageSize());
                    resultDto.setPageNo(shareList.getPageNo());
                    resultDto.setPageCount(shareList.getPageCount());
                    return ReturnResult.success(resultDto);
                } else {
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
            }else {
                return ReturnResult.failure("未获取到登录用户信息");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

}
