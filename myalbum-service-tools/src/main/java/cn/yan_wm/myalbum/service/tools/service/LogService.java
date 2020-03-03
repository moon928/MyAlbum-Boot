package cn.yan_wm.myalbum.service.tools.service;


import cn.yan_wm.myalbum.service.tools.server.Server;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 日志Sercvice
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface LogService {

    /**
     * 获取服务的日志文件信息
     * @param server
     * @param servicePath
     * @return
     */
    List<String> monitoringLog(Server server, String servicePath);

}
