package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.TbService;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysAdminMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SysAdminServiceImpl extends BaseServiceImpl<SysAdminExtend> implements SysAdminService {
    @Autowired
    private SysAdminMapper adminMapper;

    @Override
    public Mapper<SysAdminExtend> getMapper() {
        return adminMapper;
    }

    @Override
    public boolean unique(String property, String value) {
        Example example = new Example(SysAdmin.class);
//        example.createCriteria().andEqualTo(property, value);
        example.createCriteria().andCondition("BINARY "+property+"=",value);
        int result = adminMapper.selectCountByExample(example);
        if (result > 0) {
            return false;
        }
        return true;
    }

    @Override
    public SysAdmin save(SysAdmin sysAdmin) {
        /*result 表示受影响的行数*/
        int result = 0;
        //创建
        if (sysAdmin.getId()==null){
            result = adminMapper.add(sysAdmin);
        }
        //更新
        else {
            result = adminMapper.update(sysAdmin);
        }
        //保存数据成功
        if (result>0){
            return sysAdmin;
        }
        //保存失败
        return null;
    }

    @Override
    public SysAdminExtend getByUsername(String username) {
        return adminMapper.findByUsername(username);
    }

    @Override
    public List<SysAdminExtend> getAll() {
        return adminMapper.findAll();
    }


    @Override
    public DataSet<SysAdminExtend> page(Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        /*ASC是根据id 正向排序，DESC是反向排序 */
        PageHelper.orderBy("id DESC");
        PageInfo<SysAdminExtend> pageInfo = new PageInfo<>(getAll());
        DataSet<SysAdminExtend> data = super.dataSet(pageInfo);
        return data;
    }


}

