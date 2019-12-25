package cn.yan_wm.myalbum.service.tools.service;


import cn.yan_wm.myalbum.service.tools.server.Server;

public interface logService {
    //监控日志文件
    String monitoringLog(Server server, String servicePath);

}
