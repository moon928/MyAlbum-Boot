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
@Table(name = "tb_like_record")
public class TbLikeRecord extends AbstractBaseDomain {
    private static final long serialVersionUID = 2050982693971467082L;
//    /**
//     * 点赞记录表 自增id
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    /**
     * 点赞人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 稿件id
     */
    @Column(name = "image_show_id")
    private Integer imageShowId;

    /**
     * 点赞时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取点赞人id
     *
     * @return user_id - 点赞人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置点赞人id
     *
     * @param userId 点赞人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取稿件id
     *
     * @return image_show_id - 稿件id
     */
    public Integer getImageShowId() {
        return imageShowId;
    }

    /**
     * 设置稿件id
     *
     * @param imageShowId 稿件id
     */
    public void setImageShowId(Integer imageShowId) {
        this.imageShowId = imageShowId;
    }

    /**
     * 获取点赞时间
     *
     * @return create_time - 点赞时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置点赞时间
     *
     * @param createTime 点赞时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}