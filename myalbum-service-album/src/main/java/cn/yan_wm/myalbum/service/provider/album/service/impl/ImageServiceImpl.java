package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbImageMapper;
import cn.yan_wm.myalbum.service.provider.album.service.ImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
 * @description: ImageService实现层
 * @author: yan_zt
 * @create: 2020-05-05 21:24
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
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

    @Override
    public List<TbImage> listImageByFileId(String fileId) {
        return imageMapper.listImageNyFileId(fileId);
    }

    @Override
    public int countImage() {
        TbImage image = new TbImage();
        return imageMapper.selectCount(image);
    }

    @Override
    public int updateImageToAlbum(Integer albumId, Integer imageId) {
        int i = imageMapper.updateImageToAlbum(albumId, imageId);
        return i;
    }

    public List<TbImage> listImageByAlbumId(Integer albumId){
        Example example = new Example(TbImage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId",albumId);
        return imageMapper.selectByExample(example);
    }
}
