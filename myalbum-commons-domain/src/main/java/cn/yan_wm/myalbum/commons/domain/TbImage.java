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
@Table(name = "tb_image")
public class TbImage extends AbstractBaseDomain {

    /** 所属用户id */
    @Column(name = "user_id")
    private Integer userId;

    /** imgUrl 修改为这三个 服务器地址 */
    @Column(name = "url")
    private String url;

    /** 图片的fileId*/
    @Column(name = "file_id")
    private String fileId;

    /** 图片存放的组名 */
    @Column(name = "group_name")
    private String groupName;
    /** 文件名 */
    @Column(name = "file_name")
    private String fileName;
    /** 图片大小 */
    @Column(name = "img_size")
    private Double imgSize;
    /** 图片可见权限id */
    @Column(name = "visible_permission_id")
    private Integer visiblePermissionId;
    /** 图片分组id */
    @Column(name = "group_id")
    private Integer groupId;

    /** 图片分类id */
    @Column(name = "gategory_id")
    private Integer gategoryId;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

}