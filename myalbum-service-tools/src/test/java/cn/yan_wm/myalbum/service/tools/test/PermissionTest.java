package cn.yan_wm.myalbum.service.tools.test;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;

import cn.yan_wm.myalbum.service.tools.service.BackstageFeignService;
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
public class PermissionTest {



    @Autowired
    private BackstageFeignService backstageFeignService;


    @Test
    public void backstageFeignServiceTest(){
        ReturnResult<List<SysPermission>> result = backstageFeignService.getSysPermissionByZuulPrefix("TOOLS", "1686313466@qq.com");
        System.out.println(result);
    }
}
