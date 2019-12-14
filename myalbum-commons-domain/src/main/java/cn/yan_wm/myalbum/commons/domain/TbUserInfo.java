package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "tb_user_info")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TbUserInfo extends AbstractBaseDomain{
//    /**
//     * 用户信息表
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别id
     */
    @Column(name = "gender_id")
    private Integer genderId;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}