package cn.yan_wm.myalbum.service.linux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum",exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableSwagger2 //开启swagger2
@EnableScheduling
@EnableAsync
public class MyAlbumServiceLinuxApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceLinuxApplication.class,args);
    }
}
