package cn.yan_wm.myalbum.service.provider.backstage.test.service;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 权限Service测试类
 * @author: yan_zt
 * @create: 2020-04-23 19:29
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysPermissionServiceTest {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Test
    public void test(){
        List<SysPermission> permissionList = sysPermissionService.getSysPermissionByZuulPrefix("BACKSTAGE","");
        System.out.println(permissionList);
    }
}
