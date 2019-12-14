package cn.yan_wm.myalbum.service.linux.service;

import cn.yan_wm.myalbum.commons.domain.Ram;
import cn.yan_wm.myalbum.commons.domain.Disk;
import cn.yan_wm.myalbum.service.linux.server.Server;
import org.springframework.scheduling.annotation.Async;

public interface MonitorService {
    //监控磁盘
    Disk monitorDisk(Server server);
    //监控cpu
    @Async
    String monitorCpu(Server server);

    //监控内存
    Ram monitorRam(Server server);
}
