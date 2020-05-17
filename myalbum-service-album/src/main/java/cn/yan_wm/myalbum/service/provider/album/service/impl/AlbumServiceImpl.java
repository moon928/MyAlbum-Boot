package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbGroupMapper;
import cn.yan_wm.myalbum.service.provider.album.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.Arrays;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 相册管理Service实现
 * @author: yan_zt
 * @create: 2020-03-29 12:38
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class AlbumServiceImpl extends BaseServiceImpl<TbGroup> implements AlbumService {

    @Autowired
    private TbGroupMapper groupMapper;

    @Override
    public Mapper<TbGroup> getMapper() {
        return groupMapper;
    }

    public List<TbGroup> getAlbumById(Integer id,Integer[] visiblePermissionIds){
        Example example = new Example(TbGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",id);
        criteria.andIn("visiblePermissionId", Arrays.asList(visiblePermissionIds));
        return groupMapper.selectByExample(example);
    }

    @Override
    public DataSet<TbGroup> pageAlbumByUserId(Integer userId,Page page,Integer[] visiblePermissionIds) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbGroup> pageInfo = new PageInfo<>(getAlbumById(userId,visiblePermissionIds));
        DataSet<TbGroup> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public int getAlbumNum(Integer userId) {
        TbGroup tbGroup = new TbGroup();
        tbGroup.setUserId(userId);
        return groupMapper.selectCount(tbGroup);
    }

    @Override
    public int addAlbum(TbGroup group) {
        return groupMapper.insert(group);
    }

    @Override
    public int update(TbGroup tbGroup) {
        int i = groupMapper.update(tbGroup);
        return i;
    }

    @Override
    public int countImageByAlbumId(Integer albumId) {
        return groupMapper.countImageByAlbumId(albumId);
    }

}
