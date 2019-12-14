package cn.yan_wm.myalbum.commons.domainExtend.user;

import cn.yan_wm.myalbum.commons.domain.TbUserFriend;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserFriendExtend extends TbUserFriend {
    private UserInfoExtend friend;
}
