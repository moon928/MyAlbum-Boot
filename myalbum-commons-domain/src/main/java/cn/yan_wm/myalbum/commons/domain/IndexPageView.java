package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.util.Date;

/**
 * @program: MyAlbum-Boot
 * @description: 首页访问记录表
 * @author: yan_zt
 * @create: 2020-01-10 15:19
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "index_page_view")
public class IndexPageView extends AbstractBaseDomain {

    /**
     * 访问次数
     */
    private int frequency;


    /**
     * 访问时间
     */
    private Date time;
}
