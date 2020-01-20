package cn.yan_wm.myalbum.commons.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 通用的领域模型
 */

@Data
public abstract class AbstractBaseDomain implements Serializable {
    private static final long serialVersionUID = 8796860946160892438L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    /** 主键. */
    @Id
    @Column(name = "ID")
    @ApiModelProperty(value = "主键")
    private Long id;

//    /** 创建人. */
//    @Column(name = "CREATE_BY", updatable = false)
//    @ApiModelProperty(value = " 创建人")
//    private Long createBy;
//
//    /** 创建时间. */
//    @Column(name = "CREATE_TIME", updatable = false)
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;
//
//    /** 更新人. */
//    @Column(name = "UPDATE_BY")
//    @ApiModelProperty(value = "更新 人")
//    private Long updateBy;
//
//    /** 更新时间. */
//    @Column(name = "UPDATE_TIME")
//    @ApiModelProperty(value = "更新时间")
//    private Date updateTime;
}
