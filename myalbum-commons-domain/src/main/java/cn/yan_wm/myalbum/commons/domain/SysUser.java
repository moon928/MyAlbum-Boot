package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sys_user")
public class SysUser extends AbstractBaseDomain {
    /**
     * 邮箱，及登陆账号
     */
    private String username;

    /**
     * 密码（加密）
     */
    @JsonIgnore
    private String password;

    /**
     * 状态（0正常；1锁定；-1 逻辑删除）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}