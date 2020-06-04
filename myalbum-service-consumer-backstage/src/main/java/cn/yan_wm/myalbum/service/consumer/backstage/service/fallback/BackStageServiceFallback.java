package cn.yan_wm.myalbum.service.consumer.backstage.service.fallback;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.service.consumer.backstage.service.BackStageService;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 后台数据统计Service熔断处理
 * @author: yan_zt
 * @create: 2020-01-13 09:24
 */
public class BackStageServiceFallback implements BackStageService {
    @Override
    public List<BackstageIndexDto> getNewUsers(int type, int month) {
        return null;
    }

    @Override
    public List<BackstageIndexDto> getVipDistribution() {
        return null;
    }
}
