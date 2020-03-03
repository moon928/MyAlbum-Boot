package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.TbService;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 系统服务色Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbServiceService {
    /**
     * 获取服务列表
     * @param page
     * @return
     */
    DataSet<TbService> page(Page page);

    /**
     * 保存服务信息
     * @param tbService
     * @return
     */
    int save(TbService tbService);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
