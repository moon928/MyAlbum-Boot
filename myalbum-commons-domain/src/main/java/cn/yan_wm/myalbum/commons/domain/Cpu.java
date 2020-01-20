package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *     %user：CPU处在用户模式下的时间百分比。
 *
 *     %nice：CPU处在带NICE值的用户模式下的时间百分比。
 *
 *     %system：CPU处在系统模式下的时间百分比。
 *
 *      %iowait：CPU等待输入输出完成时间的百分比。
 *
 *      %steal：管理程序维护另一个虚拟处理器时，虚拟CPU的无意识等待时间百分比。
 *
 *      %idle：CPU空闲时间百分比。
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cpu extends AbstractBaseDomain {
    /** 显示了在执行用户(应用)层时的CPU利用率 */
    private float user;
    /** 显示了在以nice优先级运行用户层的CPU利用率 */
    private float nice;
    /** 显示了在执行系统(内核)层时的CPU利用率 */
    private float system;
    /** 显示了CPU在I/O请求挂起时空闲时间的百分比 */
    private float iowait;
    /** 显示了当hypervisor正服务于另外一个虚拟处理器时无意识地等待虚拟CPU所占有的时间百分比 */
    private float steal;
    /** 显示了CPU在I/O没有挂起请求时空闲时间的百分比 */
    private float idle;
    /** 时间 */
    private Date time;
}

