package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysRoleMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysRoleService;
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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleExtend> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Mapper<SysRoleExtend> getMapper() {
        return sysRoleMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public int add(SysRole role) {
        return sysRoleMapper.add(role);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteById(Long id) {
        return sysRoleMapper.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public int update(SysRole role) {
        return sysRoleMapper.update(role);
    }

    @Override
    public SysRoleExtend getById(Long id) {
        return sysRoleMapper.findById(id);
    }

    @Override
    public List<SysRoleExtend> getAll() {
        return sysRoleMapper.findAll();
    }

    @Override
    public DataSet<SysRoleExtend> page(Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<SysRoleExtend> pageInfo = new PageInfo<SysRoleExtend>(getAll());
        DataSet<SysRoleExtend> data = super.dataSet(pageInfo);
        return data;
    }


}
