package cn.yan_wm.myalbum.commons.service.framework.base;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.framework.translate.TranslateService;
import cn.yan_wm.myalbum.commons.model.DataSet;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.Date;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 基础的Service实现层
 * @author: yan_zt
 * @create: 2020-01-14 11:29
 */
public abstract class BaseServiceImpl<T> {
    @Autowired
//    protected TranslateService translateService;

    public BaseServiceImpl() {
    }

    public abstract Mapper<T> getMapper();

//    private void setCreateInfo(OperContext operContext, T t, boolean generateId) {
//        if (t instanceof AbstractBaseDomain) {
//            AbstractBaseDomain baseEntity = (AbstractBaseDomain)t;
//            if (generateId) {
//                baseEntity.setId(IdWorker.getInstance().getId());
//            }
//
//            baseEntity.setCreateBy(operContext.getOperUserId());
//            baseEntity.setCreateTime(new Date());
//        }
//    }
//
//    private void setUpdateInfo(OperContext operContext, T t) {
//        if (t instanceof AbstractBaseDomain) {
//            AbstractBaseDomain baseEntity = (AbstractBaseDomain)t;
//            baseEntity.setUpdateBy(operContext.getOperUserId());
//            baseEntity.setUpdateTime(new Date());
//        }
//    }
//
//    public int insert(OperContext operContext, T t) {
//        return this.insert(operContext, t, true);
//    }
//
//    public int insert(OperContext operContext, T t, boolean generateId) {
//        this.setCreateInfo(operContext, t, generateId);
//        return this.getMapper().insert(t);
//    }
//
//    public int insertSelective(OperContext operContext, T t) {
//        this.setCreateInfo(operContext, t, true);
//        return this.getMapper().insertSelective(t);
//    }
//
//    public int updateByPrimaryKey(OperContext operContext, T t) {
//        this.setUpdateInfo(operContext, t);
//        return this.getMapper().updateByPrimaryKey(t);
//    }
//
//    public int updateByPrimaryKeySelective(OperContext operContext, T t) {
//        this.setUpdateInfo(operContext, t);
//        return this.getMapper().updateByPrimaryKeySelective(t);
//    }
//
//    public T selectByPrimaryKey(Long id) {
//        return this.selectByPrimaryKey(id, true);
//    }
//
//    public T selectByPrimaryKey(Long id, boolean translateFlag) {
//        T t = this.getMapper().selectByPrimaryKey(id);
//        if (translateFlag) {
//            this.translateService.translate(t);
//        }
//
//        return t;
//    }
//
//    public T selectOneByExample(Example example) {
//        return this.selectOneByExample(example, true);
//    }
//
//    public T selectOneByExample(Example example, boolean translateFlag) {
//        T t = this.getMapper().selectOneByExample(example);
//        if (translateFlag) {
//            this.translateService.translate(t);
//        }
//
//        return t;
//    }
//
//    public List<T> selectByExample(Example example) {
//        return this.selectByExample(example, true);
//    }
//
//    public List<T> selectByExample(Example example, boolean translateFlag) {
//        List<T> list = this.getMapper().selectByExample(example);
//        if (translateFlag) {
//            this.translateService.translate(list);
//        }
//
//        return list;
//    }
//
//    public DataSet<T> selectByExampleAndRowBounds(Page page, Example example) {
//        return this.selectByExampleAndRowBounds(page, example, true);
//    }
//
//    public DataSet<T> selectByExampleAndRowBounds(Page page, Example example, boolean translateFlag) {
//        List<T> list = this.getMapper().selectByExampleAndRowBounds(example, page);
//        if (translateFlag) {
//            this.translateService.translate(list);
//        }
//
//        return this.toDataSet(page, list);
//    }

    public DataSet<T> dataSet(PageInfo<T> pageInfo){
        return new DataSet(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getPages(), (int) pageInfo.getTotal(),pageInfo.getList());
    }

    public DataSet<T> dataSet(Page page,List<T> list,boolean translateFlag){
//        if (translateFlag) {
//            this.translateService.translate(list);
//        }

        return this.toDataSet(page,list);
    }

    public <T1> DataSet<T1> toDataSet(Page page, List<T1> list) {
        return new DataSet(page.getPageNo(), page.getPageSize(), page.getTotalPages(), page.getTotalCount(), list);
    }

    public int deleteByPrimaryKey(OperContext operContext, Long id) {
        return this.getMapper().deleteByPrimaryKey(id);
    }
}
