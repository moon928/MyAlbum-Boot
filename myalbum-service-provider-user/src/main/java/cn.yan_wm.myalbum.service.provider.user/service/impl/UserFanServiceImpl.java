package cn.yan_wm.myalbum.service.provider.user.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.user.mapper.TbUserFanExtendMapper;
import cn.yan_wm.myalbum.service.provider.user.service.UserFanService;
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
 * @description: 用户粉丝管理ServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class UserFanServiceImpl extends BaseServiceImpl<UserFanExtend> implements UserFanService {
    @Autowired
    private TbUserFanExtendMapper tbUserFanExtendMapper;

    @Override
    public Mapper<UserFanExtend> getMapper() {
        return tbUserFanExtendMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public int addFan(Integer userId, Integer fanId) {
        return tbUserFanExtendMapper.addFan(userId,fanId);
    }

    @Transactional(readOnly = false)
    @Override
    public int deleteFan(Integer userId, Integer fanId) {
        return tbUserFanExtendMapper.deleteFan(userId,fanId);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateFanNote(Integer userId, Integer fanId, String note) {
        return tbUserFanExtendMapper.updateFanNote(userId,fanId,note);
    }

    @Override
    public UserFanExtend getByFanId(Integer userId, Integer fanId) {
        return tbUserFanExtendMapper.findFanByFanId(userId,fanId);
    }

    @Override
    public List<UserFanExtend> getByUserId(Integer userId) {
        return tbUserFanExtendMapper.findAll(userId);
    }

    @Override
    public DataSet<UserFanExtend> page(Integer userId, Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<UserFanExtend> pageInfo = new PageInfo<UserFanExtend>(tbUserFanExtendMapper.findAll(userId));
        DataSet<UserFanExtend> data = super.dataSet(pageInfo);
        return data;
    }


}
