package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserFriendExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserFriendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户好友管理ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(readOnly = true)
public class UserFriendServiceImpl extends BaseServiceImpl<UserFriendExtend> implements UserFriendService {
    @Autowired
    private TbUserFriendExtendMapper tbUserFriendExtendMapper;

    @Override
    public Mapper<UserFriendExtend> getMapper() {
        return tbUserFriendExtendMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public int addFriend(Long userId, Long friendId) {
        return tbUserFriendExtendMapper.addFriend(userId,friendId);
    }
    @Transactional(readOnly = false)
    @Override
    public int deleteFriend(Long userId, Long friendId) {
        return tbUserFriendExtendMapper.deleteFriendByFriendId(userId,friendId);
    }
    @Transactional(readOnly = false)
    @Override
    public int updateFriendNote(Long userId, Long friendId, String note) {
        return tbUserFriendExtendMapper.updateFriendNote(userId,friendId,note);
    }

    @Override
    public UserFriendExtend getByFriendId(Long userId, Long friendId) {
        return tbUserFriendExtendMapper.findByFriendId(userId,friendId);
    }

    @Override
    public List<UserFriendExtend> getByUserId(Long userId) {
        return tbUserFriendExtendMapper.findByUserId(userId);
    }

    @Override
    public DataSet<UserFriendExtend> page(Long userId, Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<UserFriendExtend> pageInfo = new PageInfo<UserFriendExtend>(getByUserId(userId));
        DataSet<UserFriendExtend> data = super.dataSet(pageInfo);
        return data;
    }
}
