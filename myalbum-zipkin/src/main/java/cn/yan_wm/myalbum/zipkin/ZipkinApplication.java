package cn.yan_wm.myalbum.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;
/**
 * Describe this class
 *
 * @author: Yan_zt
 * @date: 2020/1/2
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class,args);
    }
}
