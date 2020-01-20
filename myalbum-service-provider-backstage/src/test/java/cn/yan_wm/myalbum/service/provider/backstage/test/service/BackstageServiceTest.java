package cn.yan_wm.myalbum.service.provider.backstage.test.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.service.provider.backstage.service.BackstageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: BackstageServiceTest
 * @author: yan_zt
 * @create: 2020-01-10 11:41
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BackstageServiceTest {
    @Autowired
    private BackstageService backstageService;

    @Test
    public void getNewUsersAWeekTest(){
        List<BackstageIndexDto> list = backstageService.getNewUsersAWeek();
        log.info("BackstageService ---- getNewUsersAWeekTest ",list);
    }

    @Test
    public void getNewUsersMonthTest(){
        List<BackstageIndexDto> list = backstageService.getNewUsersMonth(6);
        log.info("BackstageService ---- getNewUsersMonthTest ",list);
    }

    @Test
    public void getVipDistributionTest(){
        List<BackstageIndexDto> list = backstageService.getVipDistribution();
        log.info("BackstageService ---- getVipDistributionTest ");
    }
}
