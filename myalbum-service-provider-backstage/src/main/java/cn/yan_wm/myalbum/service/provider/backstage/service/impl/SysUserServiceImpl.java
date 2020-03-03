package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysUserMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserExtend> implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public Mapper<SysUserExtend> getMapper() {
        return userMapper;
    }

    @Override
    public boolean unique(String property, String value) {
        SysUserExtend userExtend = userMapper.findbyUsername(value);
        if (userExtend != null) {
            return false;
        }
        return true;
    }

    @Override
    public SysUser save(SysUser sysUser) {
        /*result 表示受影响的行数*/
        int result = 0;
        //创建
        if (sysUser.getId()==null){
            result = userMapper.add(sysUser);
        }
        //更新
        else {
            result = userMapper.update(sysUser);
        }
        //保存数据成功
        if (result>0){
            return sysUser;
        }
        //保存失败
        return null;
    }

    @Override
    public int updatePwd(Long id, String password) {
        return userMapper.updatePassword(id,password);
    }

    @Override
    public int updateUsername(Long id, String username) {
        return userMapper.updateUsername(id,username);
    }

    @Override
    public int updateStatus(Long id, int status) {
        return userMapper.updateStatus(id,status);
    }

    @Override
    public SysUserExtend getByUsername(String username) {
        return userMapper.findbyUsername(username);
    }

    @Override
    public SysUserExtend getById(Long id) {
        return userMapper.findbyId(id);
    }

    @Override
    public List<SysUserExtend> getAll() {
        return userMapper.findAll();
    }

    @Override
    public DataSet<SysUserExtend> page(Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<SysUserExtend> pageInfo = new PageInfo<>(getAll());
        DataSet<SysUserExtend> data = super.dataSet(pageInfo);
        return data;
    }
}
