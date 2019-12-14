package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "tb_user_friend")
public class TbUserFriend extends AbstractBaseDomain {
    private static final long serialVersionUID = 5367281608800625890L;

    /**
     * 用户id
     */
    @JsonIgnore
    @Column(name = "user_id")
    private Long userId;

    /**
     * 好友id
     */
    @JsonIgnore
    @Column(name = "friend_id")
    private Long friendId;

    /**
     * 好友备注
     */
    @Column(name = "friend_note")
    private String friendNote;

    /**
     * 建友时间
     */
    @Column(name = "create_time")
    private Date createTime;

}