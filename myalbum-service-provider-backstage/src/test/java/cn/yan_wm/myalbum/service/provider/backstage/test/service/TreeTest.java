package cn.yan_wm.myalbum.service.provider.backstage.test.service;

import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysPermissionMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 获取叶子节点测试
 *
 * @program: MyAlbum-Boot
 * @description:
 * @author: yan_zt
 * @create: 2020-05-08 18:16
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TreeTest {

    @Autowired
    private SysPermissionService permissionService;

    @Test
    public void jiedian(){
        String note = permissionService.note(1);
        System.out.println(note);
    }

}
