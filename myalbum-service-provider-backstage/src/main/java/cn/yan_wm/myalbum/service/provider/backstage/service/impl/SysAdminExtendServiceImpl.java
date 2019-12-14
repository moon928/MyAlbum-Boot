package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysAdminExtendMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminExtendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SysAdminExtendServiceImpl extends BaseCrudServiceImpl<SysAdminExtend, SysAdminExtendMapper> implements SysAdminExtendService<SysAdminExtend> {
    @Autowired
    private SysAdminExtendMapper adminExtendMapper;

    @Override
    public SysAdminExtend getByUsername(String username) {
        return adminExtendMapper.findByUsername(username);
    }

    @Override
    public List<SysAdminExtend> getAll() {
        return adminExtendMapper.findAll();
    }

    @Override
    public PageInfo<SysAdminExtend> adminExtendPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
//        PageHelper.orderBy("id DESC");
        PageInfo<SysAdminExtend> pageInfo = new PageInfo<>(getAll());
        return pageInfo;
    }
}

