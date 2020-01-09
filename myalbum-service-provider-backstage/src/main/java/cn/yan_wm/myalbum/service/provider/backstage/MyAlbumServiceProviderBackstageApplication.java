package cn.yan_wm.myalbum.service.provider.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableEurekaClient
@MapperScan(basePackages = {"cn.yan_wm.myalbum.commons.mapper","cn.yan_wm.myalbum.service.provider.backstage.mapper"})
/** 开启swagger2 */
@EnableSwagger2
public class MyAlbumServiceProviderBackstageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceProviderBackstageApplication.class,args);
    }
}
