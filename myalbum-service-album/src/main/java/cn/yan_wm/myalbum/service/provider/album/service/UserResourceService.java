package cn.yan_wm.myalbum.service.provider.album.service;

/**
 * @program: MyAlbum-Boot
 * @description: 用户资源信息管理Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface UserResourceService {
    /**
     * 修改vip积分
     * @param userId
     * @param score
     * @return
     */
    int updateVipScoreByUserId(Integer userId, int score);

    /**
     * 修改图片数量
     * @param userId
     * @param num
     * @return
     */
    int updateImageNum(Integer userId, int num);

//    /**
//     * 修改粉丝数量
//     * @param userId
//     * @param num
//     * @return
//     */
//    int updateFanNum(Integer userId, int num);
//
//    /**
//     * 修改关注数量
//     * @param userId
//     * @param num
//     * @return
//     */
//    int updateAttentionNum(Integer userId, int num);

}
