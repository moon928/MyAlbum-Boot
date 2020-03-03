package cn.yan_wm.myalbum.service.tools.service;

import cn.yan_wm.myalbum.commons.domain.Disk;
import cn.yan_wm.myalbum.commons.domain.Ram;
import cn.yan_wm.myalbum.service.tools.server.Server;
import org.springframework.scheduling.annotation.Async;
/**
 * @program: MyAlbum-Boot
 * @description: linux 监控Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface MonitorService {

    /**
     * 监控磁盘
     *
     * @param server
     * @throws
     * @param:
    * @return:
     * @author: Yan_zt
     * @date: 2020/1/7
     */
    Disk monitorDisk(Server server);

    /**
     * 监控cpu
     *
     * @param num
     * @param server
     * @throws 
     * @param:
    * @return:
     * @author: Yan_zt
     * @date: 2020/1/7
     */
    @Async
    String monitorCpu(String num,Server server);

    /**
     * 监控内存
     *
     * @author Yzn_zt
     * @date 2020/1/7 10:03
     * @param server
     * @exception
     * @return
     */
    Ram monitorRam(Server server);
}
