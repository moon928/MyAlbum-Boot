package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.mapper.SysUserMapper;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseCrudServiceImpl<SysUser, SysUserMapper> implements SysUserService<SysUser> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Transactional(readOnly = false)
    @Override
    public int updatePwd(Long userId, String password) {
        return sysUserMapper.updatePwd(userId,password);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateStatus(Long userId, int status) {
        return sysUserMapper.updateStatus(userId,status);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateUsername(Long userId, String username) {
        return sysUserMapper.updateUsername(userId,username);
    }
}
