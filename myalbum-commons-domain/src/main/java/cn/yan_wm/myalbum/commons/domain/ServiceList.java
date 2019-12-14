package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "t_service")
public class ServiceList extends AbstractBaseDomain {
    private String serviceName;//服务名称
    private String serverId;//服务器id
    private int port;//端口好
    private String address;//服务器所在文件夹地址
}
