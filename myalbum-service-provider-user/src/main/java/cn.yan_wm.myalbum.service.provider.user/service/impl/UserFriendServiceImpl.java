package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserFriendExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserFriendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserFriendServiceImpl extends BaseCrudServiceImpl<UserFriendExtend, TbUserFriendExtendMapper> implements UserFriendService<UserFriendExtend> {
    @Autowired
    private TbUserFriendExtendMapper tbUserFriendExtendMapper;

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
    public PageInfo<UserFriendExtend> UserFriendExtendPage(Long userId ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<UserFriendExtend> pageInfo = new PageInfo<>(tbUserFriendExtendMapper.findByUserId(userId));
        return pageInfo;
    }
}
