package cn.yan_wm.myalbum.service.provider.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableEurekaClient
/** "cn.yan_wm.myalbum.commons.mapper" */
@MapperScan(basePackages = {"cn.yan_wm.myalbum.service.provider.backstage.mapper"})
/** 开启swagger2 */
@EnableSwagger2
public class MyAlbumServiceProviderBackstageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceProviderBackstageApplication.class,args);
    }
}
