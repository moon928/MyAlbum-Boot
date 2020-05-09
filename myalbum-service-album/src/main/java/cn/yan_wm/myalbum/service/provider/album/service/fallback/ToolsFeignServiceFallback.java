package cn.yan_wm.myalbum.service.provider.album.service.fallback;

import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.module.FastDFSFile;
import cn.yan_wm.myalbum.service.provider.album.service.ToolsFeignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 工具服务熔断处理类
 * @author: yan_zt
 * @create: 2020-05-05 20:18
 */
@Component
public class ToolsFeignServiceFallback implements ToolsFeignService {
    @Override
    public ReturnResult<List<FastDFSFile>> upload(List<FastDFSFile> fastDFSFileList) {
        return null;
    }

    @Override
    public ReturnResult delete(List<FastDFSFile> fastDFSFileList) {
        return null;
    }
}
