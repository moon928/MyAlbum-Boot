package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 相册管理Service
 * @author: yan_zt
 * @create: 2020-03-29 12:37
 */
public interface AlbumService {
    /**
     * 获取相册信息（分页）
     * @param userId
     * @param page
     * @return
     */
    DataSet<TbGroup> pageAlbumByUserId(Integer userId,Page page,Integer[] visiblePermissionIds);

    /**
     * 获取已有的相册数
     * @param userId
     * @return
     */
    int getAlbumNum(Integer userId);

    /**
     * 添加相册
     * @param group
     * @return
     */
    int addAlbum(TbGroup group);

    /**
     * 修改相册
     * @param tbGroup
     * @return
     */
    int update(TbGroup tbGroup);

    /**
     * 通过相册id 查询该相册的图片总数
     * @param albumId
     * @return
     */
    int countImageByAlbumId(Integer albumId);

}
