package cn.yan_wm.myalbum.service.provider.album.mapper;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.model.DataSet;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbImageMapper extends MyMapper<TbImage> {
    List<TbImage> all();

    /**
     * 批量添加图片
     * @param imageList
     * @return
     */
    int insertImages(@Param("imageList") List<TbImage> imageList);

    /**
     * 通过ids查询图片
     * @param imageIds
     * @return
     */
    List<TbImage> listImageByIds(@Param("imageIds") Integer[] imageIds);

    /**
     * 批量删除图片
     * @return
     */
    int deleteImageByIds(@Param("imageIds") Integer[] imageIds);

    /**
     * 通过 fileId 查询图片
     * @param fileId
     * @return
     */
    List<TbImage> listImageNyFileId(String fileId);


    /**
     * 移动图片分组
     * @param albumId
     * @param imageId
     */
    int updateImageToAlbum(@Param("albumId") Integer albumId,@Param("imageId") Integer imageId);
}