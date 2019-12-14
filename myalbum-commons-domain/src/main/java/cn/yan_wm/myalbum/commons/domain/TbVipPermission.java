package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_vip_permission")
public class TbVipPermission extends AbstractBaseDomain {

    /**
     * 最多存储图片数量
     */
    @Column(name = "max_img_num")
    private Integer maxImgNum;

    /**
     * 最大图片大小
     */
    @Column(name = "max_img_size")
    private Double maxImgSize;

    /**
     * 最大相册组数量
     */
    @Column(name = "max_group_num")
    private Integer maxGroupNum;

}