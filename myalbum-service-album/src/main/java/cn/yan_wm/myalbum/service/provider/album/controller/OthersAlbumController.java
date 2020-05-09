package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.album.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 他人相册
 * @author: yan_zt
 * @create: 2020-03-03 14:21
 */
@Slf4j
@RestController
@RequestMapping(value = "/othersAlbum",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OthersAlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BackstageFeignService backstageFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/pageOthersAlbum")
    @ApiOperation(value = "查看他人相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "othersId", value = "他人的id", required = true, paramType = "query", dataType = "Integer")
    })
    public ReturnResult<DataSet<TbGroup>> listOthersAlbum(@RequestParam("othersId") Integer othersId, @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                ReturnResult<Integer> userFeignResult = userFeignService.relationshipWithOthers(result.getObject().getId(), othersId);
                if (userFeignResult != null && userFeignResult.isSuccess()){
                    Integer[] visiblePermissionIds = new Integer[]{3};
                    //2表示是粉丝关系
                    if (userFeignResult.getObject() == 2){
                        visiblePermissionIds = new Integer[]{2, 3};
                    }
                    DataSet<TbGroup> dataSet = albumService.pageAlbumByUserId(othersId, page, visiblePermissionIds);
                    return ReturnResult.success(dataSet);
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
       return ReturnResult.failure("未找到相应数据");
    }

    @GetMapping("/pageImageByAlbumId")
    @ApiOperation(value = "查看他人相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "othersId", value = "他人的id", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "albumId", value = "他人相册id", required = true, paramType = "query", dataType = "Integer")
    })
    public ReturnResult<DataSet<TbImage>> listImageByAlbumId(@RequestParam("othersId") Integer othersId,@RequestParam("albumId") Integer albumId, @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                ReturnResult<Integer> userFeignResult = userFeignService.relationshipWithOthers(result.getObject().getId(), othersId);
                if (userFeignResult != null && userFeignResult.isSuccess()){
                    Integer[] visiblePermissionIds = new Integer[]{3};
                    //2表示是粉丝关系
                    if (userFeignResult.getObject() == 2){
                        visiblePermissionIds = new Integer[]{2, 3};
                    }
                    Page albumPage = new Page();
                    albumPage.setPageNo(1);
                    albumPage.setPageSize(100);
                    DataSet<TbGroup> dataSet = albumService.pageAlbumByUserId(othersId, albumPage, visiblePermissionIds);
                    //判断索要查看的相册是否在可见权限内
                    for (TbGroup group: dataSet.getRows()){
                        if (group.getId()== albumId){
                            DataSet<TbImage> imageList = imageService.pageImageByAlbumId(albumId, page);
                            return ReturnResult.success(imageList);
                        }
                    }
                    return ReturnResult.failure("您所查看的相册不在可见范围内或者相册id有误");

                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }
}
