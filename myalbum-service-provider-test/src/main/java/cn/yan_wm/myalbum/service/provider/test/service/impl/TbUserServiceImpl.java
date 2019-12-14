package cn.yan_wm.myalbum.service.provider.test.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.mapper.TbUserInfoMapper;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.test.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUserInfo, TbUserInfoMapper> implements TbUserService<TbUserInfo> {
}
