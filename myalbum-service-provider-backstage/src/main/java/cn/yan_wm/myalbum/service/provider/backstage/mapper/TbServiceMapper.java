package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.TbService;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbServiceMapper extends MyMapper<TbService> {

    /**
     * 查询所有服务列表
     * @return
     */
    List<TbService> findAll();

    /**
     * 添加服务信息
     * @param tbService
     * @return
     */
    int add(TbService tbService);

    /**
     * 更新服务信息
     * @param tbService
     * @return
     */
    int update(TbService tbService);

    /**
     * 根据id删除服务信息
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);
}
