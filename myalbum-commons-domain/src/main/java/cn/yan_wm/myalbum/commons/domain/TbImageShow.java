package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "tb_image_show")
public class TbImageShow extends AbstractBaseDomain {
    private static final long serialVersionUID = -4295724402595650891L;

    /**
     * 发表人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 发表内容
     */
    @ApiModelProperty(value = "发表内容")
    private String content;

    /**
     * 图片ids 已逗号分隔
     */
    @ApiModelProperty(value = "图片ids 已逗号分隔")
    @Column(name = "img_ids")
    private String imgIds;

    /**
     * 点赞人数
     */
    @Column(name = "like_num")
    private Integer likeNum;

    /**
     * 浏览人数
     */
    @Column(name = "browse_num")
    private Integer browseNum;

    /**
     * 可见权限id
     */
    @Column(name = "visible_permission_id")
    private Integer visiblePermissionId;

    /**
     * 发布时间
     */
    @Column(name = "create_time")
    private Date createTime;
    
}