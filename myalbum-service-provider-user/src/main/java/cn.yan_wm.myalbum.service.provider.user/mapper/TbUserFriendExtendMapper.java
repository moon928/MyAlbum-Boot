package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户好友数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbUserFriendExtendMapper extends MyMapper<UserFriendExtend> {

    /**
     * 添加好友
     * @param userId
     * @param friendId
     * @return
     */
    int addFriend(@Param("userId") Long userId,@Param("friendId") Long friendId);

    /**
     * 删除好友
     * @param userId
     * @param friendId
     * @return
     */
    int deleteFriendByFriendId(@Param("userId") Long userId,@Param("friendId") Long friendId);

    /**
     * 修改好友备注
     * @param userId
     * @param friendId
     * @param note
     * @return
     */
    int updateFriendNote(@Param("userId") Long userId,@Param("friendId") Long friendId,@Param("note") String note);

    /**
     * 查看好友信息
     * @param userId
     * @return
     */
    List<UserFriendExtend> findByUserId(@Param("userId") Long userId);

    /**
     * 通过用户id查询所有的好压
     * @param userId
     * @param friendId
     * @return
     */
    UserFriendExtend findByFriendId(@Param("userId") Long userId,@Param("friendId") Long friendId);
}