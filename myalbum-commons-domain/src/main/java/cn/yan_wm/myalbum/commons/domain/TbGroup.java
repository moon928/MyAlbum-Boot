package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "tb_group")
public class TbGroup extends AbstractBaseDomain {
    private static final long serialVersionUID = 116196585676681626L;

    /**
     * 组名
     */
    @Column(name = "name")
    private String name;

    /**
     * 所属用户
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 可见权限id
     */
    @Column(name = "visible_permission_id")
    private Integer visiblePermissionId;

    /**
     * 背景图片
     */
    @Column(name = "background")
    private String background;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}