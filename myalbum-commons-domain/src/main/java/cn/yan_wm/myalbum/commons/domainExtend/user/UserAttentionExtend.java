package cn.yan_wm.myalbum.commons.domainExtend.user;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserAttentionExtend extends TbUserAttention {
    private UserInfoExtend attention;
}
