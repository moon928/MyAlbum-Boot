package cn.yan_wm.myalbum.service.linux.service;

import cn.yan_wm.myalbum.service.linux.server.Server;

public interface logService {
    //监控日志文件
    String monitoringLog(Server server, String servicePath);

}
