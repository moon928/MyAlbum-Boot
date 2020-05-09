package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.album.service.*;
import cn.yan_wm.myalbum.service.provider.album.util.LoginUserUtile;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 相册管理
 * @author: yan_zt
 * @create: 2020-03-29 12:32
 */
@Slf4j
@RestController
@RequestMapping(value = "/album",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "相册管理")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AlbumNumberFeignService albumNumberFeignService;

    @Autowired
    private BackstageFeignService backstageFeignService;

    @GetMapping("/getMyAlbum")
    @ApiOperation(value = "获取我的相册（分页）")
    public ReturnResult<DataSet<TbGroup>> findMyAlbum(@ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                //自己能查看所有的可见权限的相册
                Integer[] visiblePermissionIds = {1,2,3};
                DataSet<TbGroup> myAlbum = albumService.pageAlbumByUserId(result.getObject().getId(), page,visiblePermissionIds);
                return ReturnResult.success(myAlbum);
            }else{
                return ReturnResult.failure("BACKSTAGE 服务异常");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure("未找到相应数据");
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加相册",notes = "json格式")
    public ReturnResult addAlbum(@RequestBody TbGroup tbGroup){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
            if (result != null && result.isSuccess() && result.getObject() != null){
                int num = albumService.getAlbumNum(result.getObject().getId());
                ReturnResult<Boolean> albumNum = albumNumberFeignService.canAddAlbum(result.getObject().getId(), num);
                if (albumNum == null ){
                    return ReturnResult.failure("BACKSTAGE 服务熔断");
                }else if(result.isSuccess()){
                    if (albumNum.getObject()){
                        tbGroup.setUserId(result.getObject().getId());
                        //如果可见权限为空则默认设置为1
                        if (tbGroup.getVisiblePermissionId()==null){
                            tbGroup.setVisiblePermissionId(1);
                        }
                        int i = albumService.addAlbum(tbGroup);
                        if (i>0){
                            return ReturnResult.success("添加成功");
                        }else{
                            return ReturnResult.failure("添加失败");
                        }
                    }else{
                        return ReturnResult.failure("相册数已达最大限数");
                    }
                }else{
                    return ReturnResult.failure("添加失败--");
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("添加失败");
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新相册",notes = "json格式")
    public ReturnResult updateAlbum(@RequestBody TbGroup tbGroup){
        try{
            int i = albumService.update(tbGroup);
            if (i>0){
                return ReturnResult.success("更新成功");
            }else{
                return ReturnResult.failure("更新失败");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
         return ReturnResult.failure("更新失败");
    }

    @GetMapping("/pageImageByAlbumId")
    @ApiOperation(value = "分页查询相册图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册ID", required = true, paramType = "query", dataType = "Integer")
    })
    public ReturnResult<DataSet<TbImage>> PageImageByAlbumId(@RequestParam("albumId") Integer albumId,@ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<TbImage> dataSet = imageService.pageImageByAlbumId(albumId, page);
            return ReturnResult.success(dataSet);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }
}
