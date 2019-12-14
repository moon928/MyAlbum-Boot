package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "linux_disk")
public class Disk extends AbstractBaseDomain {
    private String serverId;//服务器id
    private String filesystem;
    private Double size;//总大小
    private Double used;//已使用大小
    private Double avail;//可用空间
    private Double use;//所占百分比
    private Date time;
}
