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
@Table(name = "tb_user_fan")
public class TbUserFan extends AbstractBaseDomain {
    private static final long serialVersionUID = -6387854224995899007L;
    /**
     * 用户id
     */
    @JsonIgnore
    @Column(name = "user_id")
    private Long userId;

    /**
     * 粉丝id
     */
    @JsonIgnore
    @Column(name = "fan_id")
    private Long fanId;

    /**
     * 粉丝备注
     */
    @Column(name = "fan_note")
    private String fanNote;

    /**
     * 被关注时间
     */
    @Column(name = "create_time")
    private Date createTime;

}