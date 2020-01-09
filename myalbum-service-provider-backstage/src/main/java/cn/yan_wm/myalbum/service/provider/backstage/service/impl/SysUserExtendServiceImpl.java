package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysUserExtendMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserExtendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysUserExtendServiceImpl extends BaseCrudServiceImpl<SysUserExtend, SysUserExtendMapper> implements SysUserExtendService<SysUserExtend> {
    @Autowired
    private SysUserExtendMapper userExtendMapper;

    @Override
    public SysUserExtend getByUsername(String username) {
        return userExtendMapper.findbyUsername(username);
    }

    @Override
    public SysUserExtend getById(Long id) {
        return userExtendMapper.findbyId(id);
    }

    @Override
    public List<SysUserExtend> getAll() {
        return userExtendMapper.findAll();
    }

    @Override
    public PageInfo<SysUserExtend> sysUserExtendPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<SysUserExtend> pageInfo = new PageInfo<>(getAll());
        return pageInfo;
    }
}
