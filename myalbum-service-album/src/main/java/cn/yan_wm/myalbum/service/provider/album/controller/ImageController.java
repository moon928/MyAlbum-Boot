package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.domainExtend.album.ImageDto;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.module.FastDFSFile;
import cn.yan_wm.myalbum.service.provider.album.service.*;
import cn.yan_wm.myalbum.service.provider.album.utils.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private ShareService shareService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserResourceService userResourceService;

    @Value("${image.url.perfix}")
    private String IMAGE_URL_PREFIx;

    @PostMapping("/upload")
    @ApiOperation(value = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isShare", value = "是否分享（1-分享，0-不分享）", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "分享描述", paramType = "query", dataType = "String")
    })
    public ReturnResult<List<TbImage>> upload(@RequestBody List<FastDFSFile> fastDFSFileList,Integer isShare,String content){
        int i= 0;
        List<TbImage> imageList = new ArrayList<TbImage>();
        ReturnResult<SysUserExtend> result1 = new ReturnResult<SysUserExtend>();
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null){
                result1 = backstageFeignService.findByUsername((String) principal);
                if (result1 != null && result1.getObject() != null){
                    for (FastDFSFile item: fastDFSFileList){
                        String imgbase = item.getBase64();
                        imgbase=imgbase.substring(imgbase.indexOf(",")+1, imgbase.length());
                        //上传图片获取图片base64码,然后转成字节数组,以流的形式输出到本地
                        byte[] decodeBase64 = Base64.decodeBase64(imgbase);
                        String[] file = FastDFSUtils.uploadFile(decodeBase64, item.getFileName(), item.getExt());

                        TbImage image = new TbImage();
                        image.setUrl(IMAGE_URL_PREFIx+"/"+file[0]+"/"+file[1]);
                        image.setFileName(item.getFileName());
                        image.setGroupId(item.getAlbumId());
                        image.setGroupName(file[0]);
                        image.setFileId(file[1]);
                        image.setVisiblePermissionId(1);
                        image.setCreateTime(new Date());
                        image.setUserId(result1.getObject().getId());
                        imageList.add(image);
                    }
                    i = imageService.insertImages(imageList);
                    userResourceService.updateImageNum(result1.getObject().getId(),imageList.size());
                    userResourceService.updateVipScoreByUserId(result1.getObject().getId(),5);
                }else{
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
            }else{
                return ReturnResult.failure("未获取到登录的用户信息");
            }
            if (i>0 && isShare == 0){
                return ReturnResult.success(imageList);
            }else if (i>0 && isShare == 1){
                String imageids = "";
                for (int j=0; j<imageList.size();j++){
                    List<TbImage> images = imageService.listImageByFileId(imageList.get(j).getFileId());
                    if (j==imageList.size()-1){
                        imageids += images.get(0).getId() + "";
                    }else {
                        imageids += images.get(0).getId() + ",";
                    }
                }
                TbImageShow imageShow = new TbImageShow();
                imageShow.setImgIds(imageids);
                imageShow.setContent(content);
                imageShow.setUserId(result1.getObject().getId());
                imageShow.setLikeNum(0);
                imageShow.setCreateTime(new Date());
                imageShow.setBrowseNum(0);
                imageShow.setVisiblePermissionId(3);
                    //未做判断
                TbImageShow imageShow1 = shareService.saveShare(imageShow);
                if (imageShow1.getId()!= null){
                    return ReturnResult.success(imageList);
                }else{
                    return ReturnResult.failure("添加分享失败");
                }
            }else {
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


    @RequestMapping(value = "/uploadFile", method  = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传图片,MultipartFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isShare", value = "是否分享（1-分享，0-不分享）", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "分享描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "albumId", value = "相册id",required = true, paramType = "query", dataType = "Long")
    })
    public ReturnResult uploadFile(@RequestParam MultipartFile[] fileList,@RequestParam Integer isShare,String content,@RequestParam Integer albumId){
        int i= 0;
        List<TbImage> imageList = new ArrayList<TbImage>();
        ReturnResult<SysUserExtend> result1 = new ReturnResult<SysUserExtend>();
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null){
                result1 = backstageFeignService.findByUsername((String) principal);
                if (result1 != null && result1.getObject() != null){
                    for (MultipartFile item : fileList){
                        String fileName = item.getName();
                        int lastIndexOf = item.getOriginalFilename().lastIndexOf(".");
                        //获取文件的后缀名 .jpg
                        String fileExt = item.getOriginalFilename().substring(lastIndexOf+1);
                        String[] file = FastDFSUtils.uploadFile(item.getBytes(), fileName,fileExt);
                        System.out.println(IMAGE_URL_PREFIx+"/"+file[0]+"/"+file[1]);

                        TbImage image = new TbImage();
                        image.setUrl(IMAGE_URL_PREFIx+"/"+file[0]+"/"+file[1]);
                        image.setFileName(fileName);
                        image.setGroupId(albumId);
                        image.setGroupName(file[0]);
                        image.setFileId(file[1]);
                        image.setVisiblePermissionId(1);
                        image.setCreateTime(new Date());
                        image.setUserId(result1.getObject().getId());
                        imageList.add(image);
                    }
                    i = imageService.insertImages(imageList);
                    userResourceService.updateImageNum(result1.getObject().getId(),imageList.size());
                    userResourceService.updateVipScoreByUserId(result1.getObject().getId(),5);
                }else{
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
            }else{
                return ReturnResult.failure("未获取到登录的用户信息");
            }
            if (i>0 && isShare == 0){
                return ReturnResult.success(imageList);
            }else if (i>0 && isShare == 1){
                String imageids = "";
                for (int j=0; j<imageList.size();j++){
                    List<TbImage> images = imageService.listImageByFileId(imageList.get(j).getFileId());
                    if (j==imageList.size()-1){
                        imageids += images.get(0).getId() + "";
                    }else {
                        imageids += images.get(0).getId() + ",";
                    }
                }
                TbImageShow imageShow = new TbImageShow();
                imageShow.setImgIds(imageids);
                imageShow.setContent(content);
                imageShow.setUserId(result1.getObject().getId());
                imageShow.setLikeNum(0);
                imageShow.setCreateTime(new Date());
                imageShow.setBrowseNum(0);
                imageShow.setVisiblePermissionId(3);
                //未做判断
                TbImageShow imageShow1 = shareService.saveShare(imageShow);
                if (imageShow1.getId()!= null){
                    return ReturnResult.success(imageList);
                }else{
                    return ReturnResult.failure("添加分享失败");
                }
            }else {
                return ReturnResult.failure("添加失败");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("添加失败");
    }

    @PostMapping("/uploadImageToAlbum")
    @ApiOperation(value = "移动图片分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册id", paramType = "query", dataType = "Long")
    })
    public ReturnResult updateImageToAlbum(@RequestParam Integer albumId,@RequestParam(value = "imageIds[]") Integer[] imageIds){
        try{
            int i = 0;
            for (Integer imageId : imageIds){
                i = imageService.updateImageToAlbum(albumId, imageId);
            }
            if (i>0){
                return ReturnResult.success("修改成功");
            }else {
                return ReturnResult.failure("修改失败");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }
}
