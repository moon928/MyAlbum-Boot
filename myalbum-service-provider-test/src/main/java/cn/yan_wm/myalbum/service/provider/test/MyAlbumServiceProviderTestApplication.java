package cn.yan_wm.myalbum.service.provider.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableDiscoveryClient
@EnableSwagger2 //开启swagger2
@MapperScan(basePackages = {"cn.yan_wm.myalbum.commons.mapper","cn.yan_wm.myalbum.service.provider.test.mapper"})
public class MyAlbumServiceProviderTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceProviderTestApplication.class,args);
    }
}
