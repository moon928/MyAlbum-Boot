package cn.yan_wm.myalbum.commons.domainExtend.user;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domain.TbUserResource;
import cn.yan_wm.myalbum.commons.domain.TbVip;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfoExtend extends TbUserInfo {
    private TbUserResource userResource;
    private TbVip vip;
}
