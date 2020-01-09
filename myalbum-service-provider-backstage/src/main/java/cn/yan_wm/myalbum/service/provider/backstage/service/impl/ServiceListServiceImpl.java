package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbService;
import cn.yan_wm.myalbum.commons.mapper.ServiceListMapper;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.service.ServiceListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ServiceListServiceImpl extends BaseCrudServiceImpl<TbService, ServiceListMapper>implements ServiceListService<TbService> {
}
