package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserInfoExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户基本信息管理ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoExtend> implements UserInfoService {
    @Autowired
    private TbUserInfoExtendMapper tbUserInfoExtendMapper;

    @Override
    public Mapper<UserInfoExtend> getMapper() {
        return tbUserInfoExtendMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public int updateUserInfo(TbUserInfo userInfo) {
        return tbUserInfoExtendMapper.updateUserInfo(userInfo);
    }

    @Override
    public UserInfoExtend getUserInfoById(Long id) {
        return tbUserInfoExtendMapper.findById(id);
    }

    @Override
    public UserInfoExtend getUserInfoByUsername(String username) {
        return tbUserInfoExtendMapper.getUserInfoByUsername(username);
    }

    @Override
    public List<UserInfoExtend> getAll() {
        return tbUserInfoExtendMapper.findAll();
    }

    @Override
    public List<UserInfoExtend> getByNickName(String nickName) {
        return tbUserInfoExtendMapper.findByNickName(nickName);
    }

    @Override
    public DataSet<UserInfoExtend> getByNickNamePage(String nickName, Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<UserInfoExtend> pageInfo = new PageInfo<UserInfoExtend>(getByNickName(nickName));
        DataSet<UserInfoExtend> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public DataSet<UserInfoExtend> getUserInfoExtendPage(Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<UserInfoExtend> pageInfo = new PageInfo<UserInfoExtend>(getAll());
        DataSet<UserInfoExtend> data = super.dataSet(pageInfo);
        return data;
    }
}
