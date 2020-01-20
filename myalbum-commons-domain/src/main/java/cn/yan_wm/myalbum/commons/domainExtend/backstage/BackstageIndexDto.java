package cn.yan_wm.myalbum.commons.domainExtend.backstage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: MyAlbum-Boot
 * @description: 后台首页数据返回实体类
 * @author: yan_zt
 * @create: 2020-01-10 10:50
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BackstageIndexDto {
    /** 标题 */
    private String title;

    /** 数量 */
    private Integer number;

    /** 时间 */
    private String time;

    /**
     * 总条数
     */
    private Integer total;

}
