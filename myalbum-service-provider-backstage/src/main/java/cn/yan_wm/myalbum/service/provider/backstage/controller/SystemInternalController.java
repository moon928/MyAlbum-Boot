package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: MyAlbum-Boot
 * @description: 系统内部调用url
 * @author: yan_zt
 * @create: 2020-04-09 19:21
 */
@Slf4j
@RestController
@RequestMapping(value = "/system",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "系统内部调用url",hidden = true)
public class SystemInternalController {

    public ReturnResult getPermissionAndRole(String serviceName){
        return null;
    }
}
