package cn.yan_wm.myalbum.service.provider.backstage.test.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysPermissionService;
import cn.yan_wm.myalbum.service.provider.backstage.service.VipPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: VIP权限关联表Service测试
 * @author: yan_zt
 * @create: 2020-01-10 09:13
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class VipPermissionServiceTest {
    @Autowired
    private VipPermissionService vipPermissionService;

    @Test
    public void canAddAlbumTest(){
        log.info("*******************");
        Boolean a = vipPermissionService.canAddAlbum(23, 2);
        Boolean b = vipPermissionService.canAddAlbum(23, 3);
        Boolean c = vipPermissionService.canAddAlbum(23, 4);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        log.info("*******************");
    }
}
