package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbImageMapper;
import cn.yan_wm.myalbum.service.provider.album.service.ImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: ImageService实现层
 * @author: yan_zt
 * @create: 2020-05-05 21:24
 */
@Service
public class ImageServiceImpl extends BaseServiceImpl<TbImage>  implements ImageService {

    @Autowired
    private TbImageMapper imageMapper;

    @Override
    public Mapper<TbImage> getMapper() {
        return imageMapper;
    }

    @Override
    public int insertImages(List<TbImage> imageList) {
        int i = imageMapper.insertImages(imageList);
        return i;
    }

    @Override
    public List<TbImage> listImageByIds(Integer[] imageIds) {
        List<TbImage> imageList = imageMapper.listImageByIds(imageIds);
        return imageList;
    }

    @Override
    public int deleteImageByIds(Integer[] imageIds) {
        int i = imageMapper.deleteImageByIds(imageIds);
        return i;
    }

    @Override
    public DataSet<TbImage> pageImageByAlbumId(Integer albumId, Page page) {
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        PageInfo<TbImage> pageInfo = new PageInfo<>(listImageByAlbumId(albumId));
        DataSet<TbImage> data = super.dataSet(pageInfo);
        return data;
    }

    public List<TbImage> listImageByAlbumId(Integer albumId){
        Example example = new Example(TbImage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId",albumId);
        return imageMapper.selectByExample(example);
    }
}
