package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.album.ImageDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.module.FastDFSFile;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import cn.yan_wm.myalbum.service.provider.album.service.ImageService;
import cn.yan_wm.myalbum.service.provider.album.service.ToolsFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 图片Controller
 * @author: yan_zt
 * @create: 2020-03-03 14:55
 */
@Slf4j
@RestController
@RequestMapping(value = "/image",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "图片管理")
@Component
public class ImageController {

    @Autowired
    private BackstageFeignService backstageFeignService;

    @Autowired
    private ToolsFeignService toolsFeignService;

    @Autowired
    private ImageService imageService;

    @Value("${image.url.perfix}")
    private String IMAGE_URL_PREFIx;

    @PostMapping("/upload")
    @ApiOperation(value = "上传图片")
    public ReturnResult<List<TbImage>> upload(@RequestBody List<FastDFSFile> fastDFSFileList){
        int i= 0;
        List<TbImage> imageList = new ArrayList<TbImage>();
        try{

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null){
                ReturnResult<SysUserExtend> result1 = backstageFeignService.findByUsername((String) principal);
                if (result1 != null && result1.getObject() != null){
                    //上传图片
                    ReturnResult<List<FastDFSFile>> result = toolsFeignService.upload(fastDFSFileList);
                    if (result != null && result.getObject().size()>0){
                        for (FastDFSFile item : result.getObject()){
                            TbImage image = new TbImage();
                            image.setUrl(IMAGE_URL_PREFIx+"/"+item.getGroupName()+"/"+item.getFileId());
                            image.setFileName(item.getFileName());
                            image.setGroupId(item.getAlbumId());
                            image.setGroupName(item.getGroupName());
                            image.setFileId(item.getFileId());
                            image.setVisiblePermissionId(1);
                            image.setCreateTime(new Date());
                            image.setUserId(result1.getObject().getId());
                            imageList.add(image);
                        }
                        i = imageService.insertImages(imageList);
                    }else{
                        return ReturnResult.failure("TOOLS服务熔断");
                    }
                }else{
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
            }else{
                return ReturnResult.failure("未获取到登录的用户信息");
            }
            if (i>0){
                return ReturnResult.success(imageList);
            }else{
                return ReturnResult.failure("添加失败");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("添加失败");
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除图片")
    public ReturnResult delete(@RequestParam(value = "imageIds[]") Integer[] imageIds){
        //删除标记
        int i = 0;
        try{
            List<TbImage> imageList = imageService.listImageByIds(imageIds);
            List<FastDFSFile> fastDFSFileList = new ArrayList<FastDFSFile>();
            for (TbImage image:imageList){
                FastDFSFile fastDFSFile = new FastDFSFile();
                fastDFSFile.setFileId(image.getFileId());
                fastDFSFile.setGroupName(image.getGroupName());
                fastDFSFileList.add(fastDFSFile);
            }
            ReturnResult result = toolsFeignService.delete(fastDFSFileList);
            if (result != null && result.isSuccess()){
                i = imageService.deleteImageByIds(imageIds);
            }else if (result != null && result.isSuccess()){
                return ReturnResult.failure("文件删除失败");
            }else{
                ReturnResult.failure("TOOLS服务熔断");
            }

        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure("服务异常");
        }
        if (i>0){
            return ReturnResult.success("删除成功");
        }else {
            return ReturnResult.failure("数据删除失败");
        }
    }

}
