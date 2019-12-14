package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.TbVipPermissionExtendMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.VipPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VipPermissionServiceImpl extends BaseCrudServiceImpl<TbVipPermission, TbVipPermissionExtendMapper> implements VipPermissionService<TbVipPermission> {

    @Autowired
    private TbVipPermissionExtendMapper vipPermissionExtendMapper;

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
