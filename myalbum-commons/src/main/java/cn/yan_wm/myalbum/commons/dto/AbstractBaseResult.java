package cn.yan_wm.myalbum.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 通用的响应结果
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractBaseResult implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class Links {
        private String self;
        private Long total;//总条数
        private Integer pages;//总页数
        private Integer pageNum;//当前页
        private Integer pageSize;//每页条数

        private String next;
        private String last;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class DataBean<T> extends AbstractBaseDomain{
        private String type;
        private T attributes;
        private String msg;
        private T relationships;
        private Links links;
    }
}
