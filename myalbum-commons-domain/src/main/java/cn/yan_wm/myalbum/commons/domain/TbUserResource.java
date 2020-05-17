package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_user_resource")
public class TbUserResource extends AbstractBaseDomain {
    private static final long serialVersionUID = -1632854122365615768L;
//    /**
//     * 用户资源信息表 对应用户的id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    /**
     * vip积分
     */
    @Column(name = "vip_score")
    private Integer vipScore;

    /**
     * 图片总数量
     */
    @Column(name = "image_num")
    private Integer imageNum;

    /**
     * 粉丝数量
     */
    @Column(name = "fan_num")
    private Integer fanNum;

    /**
     * 关注数量
     */
    @Column(name = "attention_num")
    private Integer attentionNum;
}