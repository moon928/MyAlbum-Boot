package cn.yan_wm.myalbum.commons.domainExtend.album;

import cn.yan_wm.myalbum.commons.domain.TbLikeRecord;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import lombok.Data;

/**
 * @program: MyAlbum-Boot
 * @description: 点在记录Dto
 * @author: yan_zt
 * @create: 2020-05-17 13:47
 */
@Data
public class LikeRecordDto extends TbLikeRecord {
    /*点赞人信息*/
    UserInfoExtend user;
}
