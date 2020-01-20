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
@Table(name = "sys_permission")
public class SysPermission extends AbstractBaseDomain {

    /**
     * 权限父节点id（最顶级父节点为-1）
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限中文名称
     */
    private String name;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 网关前缀
     */
    @Column(name = "zuul_prefix")
    private String zuulPrefix;

    /**
     * 服务前缀
     */
    @Column(name = "service_prefix")
    private String servicePrefix;

    /**
     * 权限名称
     */
    private String uri;

    /**
     * 状态0 正常；1锁定 -1 逻辑删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Date updateBy;

}