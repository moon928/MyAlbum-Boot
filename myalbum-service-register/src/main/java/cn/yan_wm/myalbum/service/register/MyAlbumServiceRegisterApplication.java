package cn.yan_wm.myalbum.service.register;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum",exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients
/** 开启异步功能 */
@EnableAsync
@EnableRabbit
@EnableSwagger2
public class MyAlbumServiceRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceRegisterApplication.class,args);
    }
}
