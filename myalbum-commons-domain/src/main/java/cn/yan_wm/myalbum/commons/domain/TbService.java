package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_service")
public class TbService extends AbstractBaseDomain {
    /** 服务名称 */
    private String serviceName;
    /** 服务器id */
    private String serverId;
    /** 端口好 */
    private int port;
    /** 服务器所在文件夹地址 */
    private String address;
}
