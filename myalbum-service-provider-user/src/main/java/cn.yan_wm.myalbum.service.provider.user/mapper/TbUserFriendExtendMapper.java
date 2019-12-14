package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbUserFriendExtendMapper extends MyMapper<UserFriendExtend> {

    int addFriend(@Param("userId") Long userId,@Param("friendId") Long friendId);

    int deleteFriendByFriendId(@Param("userId") Long userId,@Param("friendId") Long friendId);

    int updateFriendNote(@Param("userId") Long userId,@Param("friendId") Long friendId,@Param("note") String note);

    List<UserFriendExtend> findByUserId(@Param("userId") Long userId);

    UserFriendExtend findByFriendId(@Param("userId") Long userId,@Param("friendId") Long friendId);
}