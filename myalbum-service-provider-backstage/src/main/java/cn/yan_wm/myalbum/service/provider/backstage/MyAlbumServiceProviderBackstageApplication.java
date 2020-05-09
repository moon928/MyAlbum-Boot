package cn.yan_wm.myalbum.service.provider.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @program: MyAlbum-Boot
 * @description: 后台服务
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@ComponentScan(basePackages = {"cn.yan_wm.myalbum"})
@EnableEurekaClient
@MapperScan(basePackages = {"cn.yan_wm.myalbum.service.provider.backstage.mapper"})
@EnableSwagger2
public class MyAlbumServiceProviderBackstageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceProviderBackstageApplication.class,args);
    }
}
