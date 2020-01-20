package cn.yan_wm.myalbum.commons.domainExtend.backstage;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: SysPermission扩展类
 * @author: yan_zt
 * @create: 2020-01-09 14:43
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysPermissionExtend extends SysPermission {
    List<SysPermissionExtend> children;
}
