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
@Table(name = "tb_image_show")
public class TbImageShow extends AbstractBaseDomain {
    private static final long serialVersionUID = -4295724402595650891L;
//    /**
//     * 稿件
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    /**
     * 发表人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 发表内容
     */
    private String content;

    /**
     * 图片ids 已逗号分隔
     */
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

    /**
     * 获取发表人id
     *
     * @return user_id - 发表人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置发表人id
     *
     * @param userId 发表人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取发表内容
     *
     * @return content - 发表内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置发表内容
     *
     * @param content 发表内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取图片ids 已逗号分隔
     *
     * @return img_ids - 图片ids 已逗号分隔
     */
    public String getImgIds() {
        return imgIds;
    }

    /**
     * 设置图片ids 已逗号分隔
     *
     * @param imgIds 图片ids 已逗号分隔
     */
    public void setImgIds(String imgIds) {
        this.imgIds = imgIds;
    }

    /**
     * 获取点赞人数
     *
     * @return like_num - 点赞人数
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * 设置点赞人数
     *
     * @param likeNum 点赞人数
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * 获取浏览人数
     *
     * @return browse_num - 浏览人数
     */
    public Integer getBrowseNum() {
        return browseNum;
    }

    /**
     * 设置浏览人数
     *
     * @param browseNum 浏览人数
     */
    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    /**
     * 获取可见权限id
     *
     * @return visible_permission_id - 可见权限id
     */
    public Integer getVisiblePermissionId() {
        return visiblePermissionId;
    }

    /**
     * 设置可见权限id
     *
     * @param visiblePermissionId 可见权限id
     */
    public void setVisiblePermissionId(Integer visiblePermissionId) {
        this.visiblePermissionId = visiblePermissionId;
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