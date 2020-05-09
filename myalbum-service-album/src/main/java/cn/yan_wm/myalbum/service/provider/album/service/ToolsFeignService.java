package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.module.FastDFSFile;
import cn.yan_wm.myalbum.service.provider.album.config.FeignConfig;
import cn.yan_wm.myalbum.service.provider.album.service.fallback.ToolsFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 上传服务 feign
 * @author: yan_zt
 * @create: 2020-03-03 15:00
 */
@FeignClient(value = "MYALBUM-TOOLS",fallback = ToolsFeignServiceFallback.class,configuration={FeignConfig.class})
public interface ToolsFeignService {
    @PostMapping(value = "/fastDFS/upload")
    public ReturnResult<List<FastDFSFile>> upload(@RequestBody List<FastDFSFile> fastDFSFileList);

    @DeleteMapping("/fastDFS/delete")
    public ReturnResult delete(@RequestBody List<FastDFSFile> fastDFSFileList);
}
