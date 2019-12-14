package cn.yan_wm.myalbum.commons.service.impl;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(readOnly = true)
public class BaseCrudServiceImpl<T extends AbstractBaseDomain, M extends MyMapper<T>> implements BaseCrudService<T> {
    @Autowired
    private M mapper;

    private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


    @Transactional(readOnly = false)
    @Override
    public T save(T domain) {
        //result 表示受影响的行数
        int result = 0;
//        Date currentDate = new Date();
        //创建
        if (domain.getId()==null){
            result = mapper.insertUseGeneratedKeys(domain);
        }
        //更新
        else {
            result = mapper.updateByPrimaryKey(domain);
        }
        //保存数据成功
        if (result>0){
            return domain;
        }
        //保存失败
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Transactional(readOnly = false)
    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }

   @Transactional(readOnly = false)
    @Override
    public int update(T t) {
        return mapper.updateByPrimaryKey(t) ;
    }

    @Override
    public int count(T t) {
        return mapper.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
   }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 分页查询
     * @param domain
     * @param pageNum  页码
     * @param pageSize 每页显示数
     * @return
     */
    @Override
    public PageInfo<T> page(T domain, int pageNum, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum,pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(mapper.select(domain));
        return pageInfo;
    }
        /**
     * 判断用户名是否唯一
     * @param value
     * @return
     */
    @Override
    public boolean unique(String property, String value) {
        Example example = new Example(entityClass);
        example.createCriteria().andEqualTo(property, value);
        int result = mapper.selectCountByExample(example);
        if (result > 0) {
            return false;
        }
        return true;
    }
}
