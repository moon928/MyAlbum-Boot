package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户好友管理Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface UserFriendService{

    /**
     * 添加好友
     * @param userId
     * @param friendId
     * @return
     */
    int addFriend(Long userId,Long friendId);

    /**
     * 删除好友
     * @param userId
     * @param friendId
     * @return
     */
    int deleteFriend(Long userId,Long friendId);

    /**
     * 修改好友备注
     * @param userId
     * @param friendId
     * @param note
     * @return
     */
    int updateFriendNote(Long userId,Long friendId,String note);

    /**
     * 查看好友基本信息
     * @param userId
     * @param friendId
     * @return
     */
    UserFriendExtend getByFriendId(Long userId,Long friendId);

    /**
     * 查询所有好友
     * @param userId
     * @return
     */
    List<UserFriendExtend> getByUserId(Long userId);

    /**
     * 分页查询好友信息
     * @param userId
     * @param page
     * @return
     */
    DataSet<UserFriendExtend> page(Long userId, Page page);
}
