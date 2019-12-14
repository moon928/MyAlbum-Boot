package cn.yan_wm.myalbum.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 通用的响应结果
 */
@Data
public abstract class AbstractBaseResult implements Serializable {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class Links {
        private String self;
        private Long total;//总条数
        private int pages;//总页数
        private int pageNum;//当前页
        private int pageSize;//每页条数

        private String next;
        private String last;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class DataBean<T> extends AbstractBaseDomain{
        private String type;
        private Long id;
        private T attributes;
        private String msg;
        private T relationships;
        private Links links;
    }
}
