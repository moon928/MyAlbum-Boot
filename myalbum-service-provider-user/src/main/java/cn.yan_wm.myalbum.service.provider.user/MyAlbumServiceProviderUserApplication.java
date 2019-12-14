package cn.yan_wm.myalbum.service.provider.user;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableDiscoveryClient
@MapperScan(basePackages = {"cn.yan_wm.myalbum.service.provider.user.mapper","cn.yan_wm.myalbum.commons.mapper"})
@EnableSwagger2 //开启swagger2
public class MyAlbumServiceProviderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceProviderUserApplication.class,args);
    }
}
