package cn.yan_wm.myalbum.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @program: MyAlbum
 * @description: 测试类
 * @author: yan_zt
 * @create: 2019-12-14 14:18
 */
@SpringBootTest
public class Test {
    @org.junit.Test
    public void test01(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
