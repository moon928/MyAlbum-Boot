package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//@Data
//@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_friend_message")
public class TbFriendMessage extends AbstractBaseDomain{
    private static final long serialVersionUID = -1275478766535637009L;

    /**
     * 发请人id
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 接受者id
     */
    @Column(name = "getter_id")
    private Integer getterId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态0：未读 1已读 2忽略 3逻辑删除
     */
    private Integer status;

    /**
     * 申请时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取发请人id
     *
     * @return sender_id - 发请人id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置发请人id
     *
     * @param senderId 发请人id
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
     * 获取状态0：未读 1已读 2忽略 3逻辑删除
     *
     * @return status - 状态0：未读 1已读 2忽略 3逻辑删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0：未读 1已读 2忽略 3逻辑删除
     *
     * @param status 状态0：未读 1已读 2忽略 3逻辑删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取申请时间
     *
     * @return create_time - 申请时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置申请时间
     *
     * @param createTime 申请时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}