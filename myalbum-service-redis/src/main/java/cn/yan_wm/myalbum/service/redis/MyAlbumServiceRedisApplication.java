package cn.yan_wm.myalbum.service.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "cn.yan_wm.myalbum",exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class MyAlbumServiceRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAlbumServiceRedisApplication.class,args);
    }
}
