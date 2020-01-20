package cn.yan_wm.myalbum.service.provider.backstage.test.service;

import cn.yan_wm.myalbum.service.provider.backstage.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: MyAlbum-Boot
 * @description: 角色权限关联表Service测试
 * @author: yan_zt
 * @create: 2020-01-10 09:13
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RolePermissionServiceTest {
    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    public void updatePermissionFromRole(){
        log.info("*******************");
        Long roleId = 1L;
        Long[] permissionId = new Long[]{3L,4L};
        int i = rolePermissionService.deletePermissionByRoleId(roleId);
        log.info("--------"+i);
        if (i>=0){
            int i1 = rolePermissionService.addpermissionForRole(roleId, permissionId);
            log.info("========="+i1);
        }
        log.info("*******************");
    }
}
