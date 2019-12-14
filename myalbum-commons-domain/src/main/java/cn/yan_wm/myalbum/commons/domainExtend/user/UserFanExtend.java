package cn.yan_wm.myalbum.commons.domainExtend.user;

import cn.yan_wm.myalbum.commons.domain.TbUserFan;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserFanExtend extends TbUserFan {
    private UserInfoExtend fan;
}
