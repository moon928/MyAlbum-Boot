package cn.yan_wm.myalbum.commons.domainExtend.backstage;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domain.TbVipPermission;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleExtend extends SysRole {
    private TbVipPermission vipPermission;

    private List<SysPermission> permissions;
}