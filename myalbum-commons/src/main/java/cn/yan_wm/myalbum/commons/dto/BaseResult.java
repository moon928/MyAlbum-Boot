package cn.yan_wm.myalbum.commons.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BaseResult<T> extends AbstractBaseDomain {
    private T data;
}
