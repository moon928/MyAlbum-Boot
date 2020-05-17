package cn.yan_wm.myalbum.service.security.oauth2.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.security.oauth2.mapper.SysUserMapper;
import cn.yan_wm.myalbum.service.security.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: MyAlbum-Boot
 * @description: UserService实现类
 * @author: yan_zt
 * @create: 2020-05-10 22:43
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class UserServiceImpl extends BaseServiceImpl<SysUserExtend> implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public Mapper<SysUserExtend> getMapper() {
        return userMapper;
    }

    @Override
    public Account getByUsername(String username) {
        SysUserExtend userExtend = userMapper.findbyUsername(username);
        Account account = new Account();
        if (userExtend != null){
            account.setId(userExtend.getId());
            account.setUsername(userExtend.getUsername());
            account.setPassword(userExtend.getPassword());
            account.setRoleExtends(userExtend.getRoleExtends());
            account.setStatus(userExtend.getStatus());
        }
        return account;
    }
}
