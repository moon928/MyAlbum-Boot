package cn.yan_wm.myalbum.service.security.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @program: MyAlbum-Boot
 * @description: sso认证服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
public class MyAlbumServiceSecurityOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceSecurityOauth2Application.class,args);
    }
}
