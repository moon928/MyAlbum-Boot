package cn.yan_wm.myalbum.service.provider.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: MyAlbum-Boot
 * @description: 相册服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"cn.yan_wm.myalbum.service.provider.album.mapper"})
@EnableSwagger2
public class MyalbumServiceProviderAlbumApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyalbumServiceProviderAlbumApplication.class,args);
    }
}
