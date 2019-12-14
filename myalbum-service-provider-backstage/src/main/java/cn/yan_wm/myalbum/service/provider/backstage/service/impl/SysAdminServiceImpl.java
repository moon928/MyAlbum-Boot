package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.mapper.SysAdminMapper;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysAdminServiceImpl extends BaseCrudServiceImpl<SysAdmin, SysAdminMapper> implements SysAdminService<SysAdmin> {

}

