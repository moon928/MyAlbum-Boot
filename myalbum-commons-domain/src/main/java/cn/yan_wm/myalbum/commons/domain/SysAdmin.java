package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sys_admin")
public class SysAdmin extends AbstractBaseDomain {
    @Size(min=5, max=15)
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private int status;

    @Column(name = "create_time")
    private Date createTime;

}
