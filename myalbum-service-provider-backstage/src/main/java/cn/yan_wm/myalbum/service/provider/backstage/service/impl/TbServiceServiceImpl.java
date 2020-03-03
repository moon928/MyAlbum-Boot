package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbService;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.TbServiceMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.TbServiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.page.Page;


import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 系统角色ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class TbServiceServiceImpl extends BaseServiceImpl<TbService> implements TbServiceService {


    @Autowired
    private TbServiceMapper tbServiceMapper;

    @Override
    public Mapper<TbService> getMapper() {
        return tbServiceMapper;
    }

    public List<TbService> findAll(){
        return tbServiceMapper.findAll();
    }

    @Override
    public DataSet<TbService> page(Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbService> pageInfo = new PageInfo<>(findAll());
        DataSet<TbService> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public int save(TbService tbService) {
        int result = 0;
        if (tbService.getId() == null){
            result = tbServiceMapper.add(tbService);
        }else{
            result = tbServiceMapper.update(tbService);
        }
        return result;
    }

    @Override
    public int deleteById(Long id) {
        return tbServiceMapper.deleteById(id);
    }
}
