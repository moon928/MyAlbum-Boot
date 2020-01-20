package cn.yan_wm.myalbum.service.tools;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: MyAlbum-Boot
 * @description: 整合了redis、email、linux、fastdfs模块
 * @author: yan_zt
 * @create: 2019-12-20 10:52
 */
@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum",exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableSwagger2 //开启swagger2
@EnableScheduling //任务
@EnableRabbit //开启基于rabbitMQ的注解
@EnableAsync
public class MyAlbumServiceToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceToolsApplication.class,args);
    }
}
