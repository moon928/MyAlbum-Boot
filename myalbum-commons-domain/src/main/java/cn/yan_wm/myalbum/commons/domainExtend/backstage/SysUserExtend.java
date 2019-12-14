package cn.yan_wm.myalbum.commons.domainExtend.backstage;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserExtend extends SysUser {
    private List<SysRoleExtend> roleExtends;
}
