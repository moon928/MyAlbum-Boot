package cn.yan_wm.myalbum.commons.domainExtend.backstage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    private Integer id;
    private String username;
    private String password;
    private int status;
    private List<SysRoleExtend> roleExtends;
}
