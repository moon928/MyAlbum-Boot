package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.service.impl.BaseCrudServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserFanExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserFanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserFanServiceImpl extends BaseCrudServiceImpl<UserFanExtend, TbUserFanExtendMapper> implements UserFanService<UserFanExtend> {
    @Autowired
    private TbUserFanExtendMapper tbUserFanExtendMapper;

    @Transactional(readOnly = false)
    @Override
    public int addFan(Long userId, Long fanId) {
        return tbUserFanExtendMapper.addFan(userId,fanId);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteFan(Long userId, Long fanId) {
        return tbUserFanExtendMapper.deleteFan(userId,fanId);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateFanNote(Long userId, Long fanId, String note) {
        return tbUserFanExtendMapper.updateFanNote(userId,fanId,note);
    }

    @Override
    public UserFanExtend getByFanId(Long userId, Long fanId) {
        return tbUserFanExtendMapper.findFanByFanId(userId,fanId);
    }

    @Override
    public List<UserFanExtend> getByUserId(Long userId) {
        return tbUserFanExtendMapper.findAll(userId);
    }

    @Override
    public PageInfo<UserFanExtend> UserFanExtendPage(Long userId ,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("id DESC");
        PageInfo<UserFanExtend> pageInfo = new PageInfo<>(tbUserFanExtendMapper.findAll(userId));
        return pageInfo;
    }
}
