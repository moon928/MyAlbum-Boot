package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
//@Data
//@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_image")
public class TbImage extends AbstractBaseDomain {

    private String name;

    /** imgUrl 修改为这三个 服务器地址 */
    private String uri;
    /** 图片存放的组名 */
    private String groupName;
    /** 文件名 */
    private String fileName;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "img_size")
    private Double imgSize;

    @Column(name = "visible_permission_id")
    private Integer visiblePermissionId;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "gategory_id")
    private Integer gategoryId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return img_size
     */
    public Double getImgSize() {
        return imgSize;
    }

    /**
     * @param imgSize
     */
    public void setImgSize(Double imgSize) {
        this.imgSize = imgSize;
    }

    /**
     * @return visible_permission_id
     */
    public Integer getVisiblePermissionId() {
        return visiblePermissionId;
    }

    /**
     * @param visiblePermissionId
     */
    public void setVisiblePermissionId(Integer visiblePermissionId) {
        this.visiblePermissionId = visiblePermissionId;
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return gategory_id
     */
    public Integer getGategoryId() {
        return gategoryId;
    }

    /**
     * @param gategoryId
     */
    public void setGategoryId(Integer gategoryId) {
        this.gategoryId = gategoryId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}