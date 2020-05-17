package cn.yan_wm.myalbum.service.provider.album.test;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbImageMapper;
import cn.yan_wm.myalbum.service.provider.album.service.BackstageFeignService;
import cn.yan_wm.myalbum.service.provider.album.service.PrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
    private TbImageMapper tbImageMapper;
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

    @Test
    public void imageMapperInsertImagesTest(){
        List<TbImage> imageList = new ArrayList<TbImage>();
        TbImage image1 = new TbImage();
        image1.setGroupName("imagea");
        image1.setGroupName("aaa");
        image1.setFileId("...aaa");
        image1.setUrl("111111111111111");
        image1.setVisiblePermissionId(3);
        image1.setGroupId(27);
        image1.setUserId(23);

        TbImage image2 = new TbImage();
        image2.setGroupName("image2222");
        image2.setGroupName("bbbb");
        image2.setFileId("...bbb");
        image2.setUrl("2222222222222222");
        image2.setVisiblePermissionId(3);
        image2.setGroupId(27);
        image2.setUserId(23);

        imageList.add(image1);
        imageList.add(image2);
        int i = tbImageMapper.insertImages(imageList);
        System.out.println(imageList);
        System.out.println(imageList);
    }

    @Test
    public void test02(){
        String s = "url(asdasdasdasdasd)";
        s = s.substring(4,s.length()-1);
        System.out.println(s);

    }
}
