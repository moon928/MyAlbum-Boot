package cn.yan_wm.myalbum.commons.domainExtend.album;

import cn.yan_wm.myalbum.commons.domain.TbCommentRecord;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: MyAlbum-Boot
 * @description: 评论返回dto
 * @author: yan_zt
 * @create: 2020-05-11 01:08
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommentDto extends TbCommentRecord {
    /*发送者信息*/
    UserInfoExtend sender;
    /*接收者信息*/
    UserInfoExtend getter;
}
