package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
//@Data
//@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_group")
public class TbGroup extends AbstractBaseDomain {
    private static final long serialVersionUID = 116196585676681626L;

    /**
     * 组名
     */
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
    private String background;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取组名
     *
     * @return name - 组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     *
     * @param name 组名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取所属用户
     *
     * @return user_id - 所属用户
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置所属用户
     *
     * @param userId 所属用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取可见权限id
     *
     * @return visible_permission_id - 可见权限id
     */
    public Integer getVisiblePermissionId() {
        return visiblePermissionId;
    }

    /**
     * 设置可见权限id
     *
     * @param visiblePermissionId 可见权限id
     */
    public void setVisiblePermissionId(Integer visiblePermissionId) {
        this.visiblePermissionId = visiblePermissionId;
    }

    /**
     * 获取背景图片
     *
     * @return background - 背景图片
     */
    public String getBackground() {
        return background;
    }

    /**
     * 设置背景图片
     *
     * @param background 背景图片
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}