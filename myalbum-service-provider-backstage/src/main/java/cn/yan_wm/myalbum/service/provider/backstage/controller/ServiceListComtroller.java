package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.ServiceList;
import cn.yan_wm.myalbum.service.provider.backstage.service.ServiceListService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceListComtroller {
    @Autowired
    private ServiceListService serviceListService;

    @ApiOperation(value = "获取服务列表")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ServiceList> getService(){
        List<ServiceList> serviceLists = serviceListService.selectAll();
        return serviceLists;
    }
}
