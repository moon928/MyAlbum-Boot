package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.album.ImageDto;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 图片管理Service
 * @author: yan_zt
 * @create: 2020-03-03 14:59
 */
public interface ImageService {
    /**
     * 批量添加图片
     * @param imageList
     * @return
     */
    int insertImages(List<TbImage> imageList);

    /**
     * 通过图片的id查找图片
     * @param imageIds
     * @return
     */
    List<TbImage> listImageByIds(Integer[] imageIds);

    /**
     * 批量删除图片
     * @param imageIds
     * @return
     */
    int deleteImageByIds(Integer[] imageIds);

    /**
     * 通过相册id查询相册的图片
     * @param albumId
     * @return
     */
    DataSet<TbImage> pageImageByAlbumId(Integer albumId, Page page);

    /**
     * 通过 fileId 查询图片
     * @param fileId
     * @return
     */
    List<TbImage> listImageByFileId(String fileId);

    /**
     * 图片总量
     * @return
     */
    int countImage();

    /**
     * 移动图片分组
     * @param albumId
     * @param imageId
     */
    int updateImageToAlbum(Integer albumId,Integer imageId);
}
