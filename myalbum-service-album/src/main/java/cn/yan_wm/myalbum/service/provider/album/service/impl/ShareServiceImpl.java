package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbImageShowMapper;
import cn.yan_wm.myalbum.service.provider.album.service.ShareService;
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
 * @description: SharePictureService实现类
 * @author: yan_zt
 * @create: 2020-05-10 01:54
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class ShareServiceImpl extends BaseServiceImpl<TbImageShow> implements ShareService {

    @Autowired
    private TbImageShowMapper imageShowMapper;

    @Override
    public Mapper<TbImageShow> getMapper() {
        return imageShowMapper;
    }

    @Override
    public TbImageShow saveShare(TbImageShow imageShow) {
        //result 表示受影响的行数
        int result = 0;
//        Date currentDate = new Date();
        //创建
        if (imageShow.getId()==null){
            result = imageShowMapper.insertUseGeneratedKeys(imageShow);
        }
        //更新
        else {
            result = imageShowMapper.updateByPrimaryKey(imageShow);
        }
        //保存数据成功
        if (result>0){
            return imageShow;
        }
        //保存失败
        return null;
    }

    @Override
    public TbImageShow getShareById(Integer id) {
        Example example = new Example(TbImageShow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);
        List<TbImageShow> list = imageShowMapper.selectByExample(example);
        if (list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }

    @Override
    public int deleteById(Integer id) {
        TbImageShow imageShow = new TbImageShow();
        imageShow.setId(id);
        return imageShowMapper.delete(imageShow);
    }

    @Override
    public DataSet<TbImageShow> pageShareByVisiblePermissionIds(List<Integer> visiblePermissionIds, Page page) {
        Example example = new Example(TbImageShow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("visiblePermissionId",visiblePermissionIds);
        List<TbImageShow> list = imageShowMapper.selectByExample(example);
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbImageShow> pageInfo = new PageInfo<>(list);
        DataSet<TbImageShow> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public DataSet<TbImageShow> pageShareByUserId(Integer userId, Page page) {
        Example example = new Example(TbImageShow.class);
        Example.Criteria criteria = example.createCriteria();
        if (userId != null){
            criteria.andEqualTo("userId",userId);
        }
//        List<TbImageShow> list = ;
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbImageShow> pageInfo = new PageInfo<>(imageShowMapper.selectByExample(example));
        DataSet<TbImageShow> data = super.dataSet(pageInfo);
        return data;
    }

    @Override
    public List<TbImageShow> listShareByUserId(Integer userId) {
        Example example = new Example(TbImageShow.class);
        Example.Criteria criteria = example.createCriteria();
        if (userId != null){
            criteria.andEqualTo("userId",userId);
        }
        return imageShowMapper.selectByExample(example);
    }
}
