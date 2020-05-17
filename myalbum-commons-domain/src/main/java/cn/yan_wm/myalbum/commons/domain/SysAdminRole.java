package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sys_admin_role")
public class SysAdminRole extends AbstractBaseDomain {
    /**
     * 用户id
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

}