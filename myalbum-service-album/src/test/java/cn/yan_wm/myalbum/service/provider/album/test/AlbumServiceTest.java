package cn.yan_wm.myalbum.service.provider.album.test;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import cn.yan_wm.myalbum.service.provider.album.service.PrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: Album测试
 * @author: yan_zt
 * @create: 2020-03-29 17:33
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AlbumServiceTest {

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private BackstageFeignService backstageFeignService;
    @Test
    public void test01(){
        ReturnResult<SysUser> userInfo = principalService.getUserInfo("831aae08-9248-47b7-a213-8171201073fb");
        System.out.println(userInfo.getObject());

    }

    @Test
    public void backstageFeignServiceTest(){
        ReturnResult<List<SysPermission>> result = backstageFeignService.getSysPermissionByZuulPrefix("ALBUM", "1686313466@qq.com");
        System.out.println(result);
    }
}
