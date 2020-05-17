package cn.yan_wm.myalbum.service.security.oauth2.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.Account;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.security.oauth2.mapper.SysAdminMapper;
import cn.yan_wm.myalbum.service.security.oauth2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @program: MyAlbum-Boot
 * @description: AdminService实现类
 * @author: yan_zt
 * @create: 2020-05-10 22:59
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class AdminServiceImpl  extends BaseServiceImpl<SysAdminExtend> implements AdminService {

    @Autowired
    private SysAdminMapper adminMapper;

    @Override
    public Mapper<SysAdminExtend> getMapper() {
        return adminMapper;
    }

    @Override
    public Account getByUsername(String username) {
        SysAdminExtend adminExtend = adminMapper.findByUsername(username);
        Account account = new Account();
        if (adminExtend != null){
            account.setId(adminExtend.getId());
            account.setUsername(adminExtend.getUsername());
            account.setPassword(adminExtend.getPassword());
            account.setRoleExtends(adminExtend.getRoleExtends());
            account.setStatus(adminExtend.getStatus());
        }
        return account;
    }
}
