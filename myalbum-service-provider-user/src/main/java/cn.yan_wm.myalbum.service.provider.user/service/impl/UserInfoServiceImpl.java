package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserInfoExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl extends BaseCrudServiceImpl<UserInfoExtend,TbUserInfoExtendMapper> implements UserInfoService<UserInfoExtend> {
    @Autowired
    private TbUserInfoExtendMapper tbUserInfoExtendMapper;

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
    public List<UserInfoExtend> getAll() {
        return tbUserInfoExtendMapper.findAll();
    }

    @Override
    public List<UserInfoExtend> getByNickName(String nickName) {
        return tbUserInfoExtendMapper.findByNickName(nickName);
    }

    @Override
    public PageInfo<UserInfoExtend> UserInfoExtendPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
//        PageHelper.orderBy("id DESC");
        PageInfo<UserInfoExtend> pageInfo = new PageInfo<>(getAll());
        return pageInfo;
    }

    @Override
    public PageInfo<UserInfoExtend> getByNickNamePage(String nickName) {
        PageHelper.startPage(1,10);
        PageInfo<UserInfoExtend> pageInfo = new PageInfo<>(getByNickName(nickName));
        return pageInfo;
    }
}
