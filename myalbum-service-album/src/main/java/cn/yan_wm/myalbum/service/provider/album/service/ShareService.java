package cn.yan_wm.myalbum.service.provider.album.service;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 分享图片Service
 * @author: yan_zt
 * @create: 2020-05-10 01:52
 */
public interface ShareService {
    /**
     * 保存分享记录
     * @param imageShow
     * @return
     */
    TbImageShow saveShare(TbImageShow imageShow);

    /**
     * 通过id查询分享记录信息
     * @param id
     * @return
     */
    TbImageShow getShareById(Integer id);

    /**
     * 通过id删除分享记录信息
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 通过标记查看指定的人的分页查询分享
     * @param visiblePermissionIds
     * @return
     */
    DataSet<TbImageShow> pageShareByVisiblePermissionIds(List<Integer> visiblePermissionIds, Page page);

    /**
     * 通过标记查看指定的人的分页查询分享
     * @param userId
     * @return
     */
    DataSet<TbImageShow> pageShareByUserId(Integer userId, Page page);

    /**
     * 通过userId获取所有的精选
     * @param userId
     * @return
     */
    List<TbImageShow> listShareByUserId(Integer userId);
}
