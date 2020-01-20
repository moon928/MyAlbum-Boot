package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.TbVipPermissionMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.VipPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class VipPermissionServiceImpl extends BaseServiceImpl<TbVipPermission> implements VipPermissionService {

    @Autowired
    private TbVipPermissionMapper vipPermissionExtendMapper;

    @Override
    public Mapper<TbVipPermission> getMapper() {
        return vipPermissionExtendMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public int add(TbVipPermission vipPermission) {
        return vipPermissionExtendMapper.add(vipPermission);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteById(Long id) {
        return vipPermissionExtendMapper.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public int update(TbVipPermission vipPermission) {
        return vipPermissionExtendMapper.update(vipPermission);
    }



}
