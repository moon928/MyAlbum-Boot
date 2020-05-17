package cn.yan_wm.myalbum.commons.domainExtend.album;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domain.TbImageShow;
import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 分享数据返回类型
 * @author: yan_zt
 * @create: 2020-05-10 02:46
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ShareDto extends TbImageShow {
    /**
     * 评论数
     */
    private Integer commentNum;
    /**
     * 是否点赞
     */
    private Boolean isLiked;
    /**
     * 是否关注
     */
    private Boolean isAttention;

    UserInfoExtend userInfo;
    List<TbImage> imageList;
}
