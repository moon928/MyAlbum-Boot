package cn.yan_wm.myalbum.service.provider.user;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;
/**
 * @program: MyAlbum-Boot
 * @description: 用户服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"cn.yan_wm.myalbum.service.provider.user.mapper"})
@EnableSwagger2
public class MyAlbumServiceProviderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceProviderUserApplication.class,args);
    }
}
