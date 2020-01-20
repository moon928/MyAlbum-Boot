package cn.yan_wm.myalbum.service.tools.service;


import cn.yan_wm.myalbum.service.tools.server.Server;

import java.util.List;

public interface logService {

    List<String> monitoringLog(Server server, String servicePath);

}
