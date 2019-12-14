package cn.yan_wm.myalbum.service.linux.controller;

import cn.yan_wm.myalbum.service.linux.server.Server;
import cn.yan_wm.myalbum.service.linux.service.logService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class MonitoringLogController {
    @Autowired
    private logService logService;

    @ApiOperation(value = "查看gateway服务的日志信息")
    @GetMapping("gateway")
    public String getGatewayLog(){
        String path = "/home/myalbum/myalbum-gateway/";
        return logService.monitoringLog(Server.SERVER01,path);
    }

    @ApiOperation(value = "查看sercurity-oauth2服务的日志信息")
    @GetMapping("oauth2")
    public String getoOauth2(){
        String path = "/home/myalbum/myalbum-service-security-oauth2/";
        return logService.monitoringLog(Server.SERVER01,path);
    }

    @ApiOperation(value = "查看consumer-test服务的日志信息")
    @GetMapping("consumerTest")
    public String getoConsumerTest(){
        String path = "/home/myalbum/myalbum-consumer-test/";
        return logService.monitoringLog(Server.SERVER02,path);
    }

    @ApiOperation(value = "查看provider-test服务的日志信息")
    @GetMapping("providerTest")
    public String getoProviderTest(){
        String path = "/home/myalbum/myalbum-provider-test";
        return logService.monitoringLog(Server.SERVER02,path);
    }

    @ApiOperation(value = "查看redis服务的日志信息")
    @GetMapping("redis")
    public String getRedis(){
        String path = "/home/myalbum/myalbum-redis";
        return logService.monitoringLog(Server.SERVER01,path);
    }

    @ApiOperation(value = "查看email服务的日志信息")
    @GetMapping("email")
    public String getEmail(){
        String path = "/home/myalbum/myalbum-email";
        return logService.monitoringLog(Server.SERVER01,path);
    }

    @ApiOperation(value = "查看register服务的日志信息")
    @GetMapping("register")
    public String getRegister(){
        String path = "/home/myalbum/myalbum-register/";
        return logService.monitoringLog(Server.SERVER02,path);
    }

    @ApiOperation(value = "查看provider-backstage服务的日志信息")
    @GetMapping("providerBackstage")
    public String getBackstage(){
        String path = "/home/myalbum/myalbum-provider-backstage/";
        return logService.monitoringLog(Server.SERVER02,path);
    }
}
