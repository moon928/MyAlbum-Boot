package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_sys_message")
public class TbSysMessage extends AbstractBaseDomain {
    private static final long serialVersionUID = -8274929423210415005L;
//    /**
//     * 系统消息 自增id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    /**
     * 发送人员id
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片ids
     */
    @Column(name = "images_ids")
    private String imagesIds;

    /**
     * 发布时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取发送人员id
     *
     * @return admin_id - 发送人员id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置发送人员id
     *
     * @param adminId 发送人员id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取图片ids
     *
     * @return images_ids - 图片ids
     */
    public String getImagesIds() {
        return imagesIds;
    }

    /**
     * 设置图片ids
     *
     * @param imagesIds 图片ids
     */
    public void setImagesIds(String imagesIds) {
        this.imagesIds = imagesIds;
    }

    /**
     * 获取发布时间
     *
     * @return create_time - 发布时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发布时间
     *
     * @param createTime 发布时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}