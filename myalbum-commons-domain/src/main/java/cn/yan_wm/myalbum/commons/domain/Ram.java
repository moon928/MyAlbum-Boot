package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ram extends AbstractBaseDomain {
    private Double total;
    private Double userd;
    private Double free;
    private Double shared;
    private Double buff_cache;
    private Double available;
    private Date time;
}
