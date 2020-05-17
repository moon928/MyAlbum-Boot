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
@Table(name = "tb_user_attention")
public class TbUserAttention extends AbstractBaseDomain {
    private static final long serialVersionUID = -1303594945285835821L;

    /**
     * 用户id
     */
    @JsonIgnore
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 关注的id
     */
    @JsonIgnore
    @Column(name = "attention_id")
    private Integer attentionId;

    /**
     * 关注的备注
     */
    @Column(name = "attention_note")
    private String attentionNote;

    /**
     * 关注时间
     */
    @Column(name = "create_time")
    private Date createTime;

}