package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//@Data
//@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_comment_record")
public class TbCommentRecord extends AbstractBaseDomain {
    private static final long serialVersionUID = 1446890469870285961L;
    /**
     * 评论者id
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 接受者id
     */
    @Column(name = "getter_id")
    private Integer getterId;

    /**
     * 稿件id
     */
    @Column(name = "iamge_show_id")
    private Integer iamgeShowId;

    /**
     * 评论时间
     */
    @Column(name = "create_time")
    private Date createTime;


    /**
     * 获取评论者id
     *
     * @return sender_id - 评论者id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置评论者id
     *
     * @param senderId 评论者id
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取接受者id
     *
     * @return getter_id - 接受者id
     */
    public Integer getGetterId() {
        return getterId;
    }

    /**
     * 设置接受者id
     *
     * @param getterId 接受者id
     */
    public void setGetterId(Integer getterId) {
        this.getterId = getterId;
    }

    /**
     * 获取稿件id
     *
     * @return iamge_show_id - 稿件id
     */
    public Integer getIamgeShowId() {
        return iamgeShowId;
    }

    /**
     * 设置稿件id
     *
     * @param iamgeShowId 稿件id
     */
    public void setIamgeShowId(Integer iamgeShowId) {
        this.iamgeShowId = iamgeShowId;
    }

    /**
     * 获取评论时间
     *
     * @return create_time - 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置评论时间
     *
     * @param createTime 评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}