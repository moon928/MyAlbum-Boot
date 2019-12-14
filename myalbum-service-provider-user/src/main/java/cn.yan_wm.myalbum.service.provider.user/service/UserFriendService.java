package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserFriendService<T extends AbstractBaseDomain> extends BaseCrudService<T>{

    int addFriend(Long userId,Long friendId);

    int deleteFriend(Long userId,Long friendId);

    int updateFriendNote(Long userId,Long friendId,String note);

    UserFriendExtend getByFriendId(Long userId,Long friendId);

    List<UserFriendExtend> getByUserId(Long userId);

    default PageInfo<T> UserFriendExtendPage(Long userId,int pageNum, int pageSize) {
        return null;
    }
}
