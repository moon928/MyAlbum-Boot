package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sys_role")
public class SysRole extends AbstractBaseDomain {

    /**
     * 角色
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色中文名
     */
    private String name;

    /**
     * vip积分下限
     */
    @Column(name = "lower_limit")
    private Integer lowerLimit;

    /**
     * vip积分上限
     */
    @Column(name = "upper_limit")
    private Integer upperLimit;

    /**
     * 状态 0：正常 1锁定 2逻辑删除
     */
    private Integer status;

    /**
     * 层级 普通 0； 管理员 1
     */
    private Integer level;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人员
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 更新人员
     */
    @Column(name = "update_by")
    private byte[] updateBy;

}