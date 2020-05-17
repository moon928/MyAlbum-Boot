package cn.yan_wm.myalbum.service.provider.album.controller;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import cn.yan_wm.myalbum.service.provider.album.service.UserInfoService;
import cn.yan_wm.myalbum.service.provider.album.utils.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: MyAlbum-Boot
 * @description: UserInfocontroller
 * @author: yan_zt
 * @create: 2020-05-16 18:49
 */
@Slf4j
@RestController
@Api(tags = "用户信息")
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserInfoController {

    @Autowired
    private BackstageFeignService backstageFeignService;

    @Autowired
    private UserInfoService userInfoService;

    @Value("${image.url.perfix}")
    private String IMAGE_URL_PREFIx;

    @RequestMapping(value = "/uploadFile", method  = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传图片用户头像,MultipartFile")
    public ReturnResult uploadUserAvatar(@RequestParam MultipartFile[] file){
        try{
            String principal = SecurityContextHolder.getContext().getAuthentication().getName();
            if (StringUtils.isNotBlank(principal)) {
                ReturnResult<SysUserExtend> result = backstageFeignService.findByUsername(principal);
                if (result != null && result.isSuccess() && result.getObject() != null) {

                    for (MultipartFile item: file){
                        String fileName = item.getName();
                        int lastIndexOf = item.getOriginalFilename().lastIndexOf(".");
                        //获取文件的后缀名 .jpg
                        String fileExt = item.getOriginalFilename().substring(lastIndexOf+1);
                        String[] fileData = FastDFSUtils.uploadFile(item.getBytes(), fileName,fileExt);

                        String avatarUrl = IMAGE_URL_PREFIx + "/"+ fileData[0] + "/" + fileData[1];

                        int i = userInfoService.updateUserAvatar(result.getObject().getId(), avatarUrl);
                        if (i>0){
                            return ReturnResult.success("上传成功");
                        }else{
                            return ReturnResult.failure("上传失败");
                        }
                    }
                } else {
                    return ReturnResult.failure("BACKSTAGE服务熔断");
                }
            }else{
                return ReturnResult.failure("未获取到用户信息");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("上传失败");
    }
}
