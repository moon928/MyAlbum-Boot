package cn.yan_wm.myalbum.commons.service;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 通用的业务逻辑
 */
public interface BaseCrudService<T extends AbstractBaseDomain> {
    /**
     * 查询属性值是否唯一
     * @param property
     * @param value
     * @return true代表唯一 ，false 代表不唯一
     */
    default boolean unique(String property, String value){
        return false;
    }

    /**
     * 保存
     * @param domain
     * @return
     */
    default T save(T domain){
        return null;
    }

    /**
     * 分页查询
     * @param domain
     * @param pageNum  页码
     * @param pageSize 每页显示数
     * @return
     */
    default PageInfo<T> page(T domain, int pageNum, int pageSize){
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setList(null);
        return pageInfo;
    }

    int insert(T t);

    int delete(T t);

    int update(T t);

    int count(T t);

    T selectOne(T t);

    List<T> selectAll();
}
