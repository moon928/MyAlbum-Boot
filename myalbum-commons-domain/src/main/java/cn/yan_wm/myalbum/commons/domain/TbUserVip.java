package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_user_vip")
public class TbUserVip extends AbstractBaseDomain {
    private static final long serialVersionUID = 5530502398821163308L;
//    /**
//     * 用户与VIP等级关联表，自增id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * vipId
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取vipId
     *
     * @return role_id - vipId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置vipId
     *
     * @param roleId vipId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}