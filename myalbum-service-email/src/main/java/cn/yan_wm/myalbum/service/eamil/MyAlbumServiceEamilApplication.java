package cn.yan_wm.myalbum.service.eamil;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum")
@EnableDiscoveryClient
@EnableRabbit //开启基于rabbitMQ的注解
@EnableAsync //开启异步功能
public class MyAlbumServiceEamilApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceEamilApplication.class,args);
    }
}
