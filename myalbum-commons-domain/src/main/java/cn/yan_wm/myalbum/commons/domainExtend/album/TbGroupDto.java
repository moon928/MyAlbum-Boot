package cn.yan_wm.myalbum.commons.domainExtend.album;

import cn.yan_wm.myalbum.commons.domain.TbGroup;
import lombok.Data;

/**
 * @program: MyAlbum-Boot
 * @description: 相册Dto
 * @author: yan_zt
 * @create: 2020-05-14 21:07
 */
@Data
public class TbGroupDto extends TbGroup {
    //相片数量
    private int imageNum;
}
