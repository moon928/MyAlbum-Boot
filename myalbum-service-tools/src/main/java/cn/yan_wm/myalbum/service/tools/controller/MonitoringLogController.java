package cn.yan_wm.myalbum.service.tools.controller;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.tools.server.Server;
import cn.yan_wm.myalbum.service.tools.service.logService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/log",produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
@Api(tags = "日志查看")
public class MonitoringLogController {
    @Autowired
    private logService logService;

    @ApiOperation(value = "查看gateway服务的日志信息")
    @GetMapping("/gateway")
    public List<String> getGatewayLog(){
        String path = "/home/myalbum/myalbum-gateway/";
        return logService.monitoringLog(Server.SERVER02,path);
    }

    @ApiOperation(value = "查看服务的日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverId", value = "所在服务器的编号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "address", value = "部署的地址", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/")
    public ReturnResult<List<String>> getLogs(@RequestParam("serverId") int serverId, @RequestParam("address") String address){
        List<String> logList = new ArrayList<String>();
        String path = address;
        if (StringUtils.isEmpty(path)){
            log.error("MonitoringLogController --- getLogs ----address不能为空");
            return ReturnResult.failure("address不能为空");
        }
        if (serverId == 1){
            logList = logService.monitoringLog(Server.SERVER01,path);
        }else if (serverId == 2){
            logList = logService.monitoringLog(Server.SERVER02,path);
        }else if(serverId == 3){
            logList = logService.monitoringLog(Server.SERVER03,path);
        }else {
            log.error("MonitoringLogController --- getLogs ----无效的 serverId ");
            return ReturnResult.failure("无效的 serverId ");
        }
        return ReturnResult.success(logList);
    }

//    @ApiOperation(value = "查看consumer-test服务的日志信息")
//    @GetMapping("consumerTest")
//    public String getoConsumerTest(){
//        String path = "/home/myalbum/myalbum-consumer-test/";
//        return logService.monitoringLog(Server.SERVER02,path);
//    }
//
//    @ApiOperation(value = "查看provider-test服务的日志信息")
//    @GetMapping("providerTest")
//    public String getoProviderTest(){
//        String path = "/home/myalbum/myalbum-provider-test";
//        return logService.monitoringLog(Server.SERVER02,path);
//    }

//    @ApiOperation(value = "查看tools服务的日志信息")
//    @GetMapping("/tools")
//    public List<String> getRedis(){
//        String path = "/home/myalbum/myalbum-tools";
//        return logService.monitoringLog(Server.SERVER02,path);
//    }


//    @ApiOperation(value = "查看register服务的日志信息")
//    @GetMapping("register")
//    public List<String> getRegister(){
//        String path = "/home/myalbum/myalbum-register/";
//        return logService.monitoringLog(Server.SERVER02,path);
//    }
//
//    @ApiOperation(value = "查看provider-backstage服务的日志信息")
//    @GetMapping("providerBackstage")
//    public List<String> getBackstage(){
//        String path = "/home/myalbum/myalbum-provider-backstage/";
//        return logService.monitoringLog(Server.SERVER02,path);
//    }
}
