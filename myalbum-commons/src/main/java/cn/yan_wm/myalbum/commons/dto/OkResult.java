package cn.yan_wm.myalbum.commons.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: OkResult 自定义返回
 * @author: yan_zt
 * @create: 2020-01-08 15:54
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class OkResult extends AbstractBaseResult{
    private int code;
    private String message;

    private Links links;
    private List<Object> data;

    public void statusCode_success(){
        this.code = 200;
        this.message = "success";
    }

    public OkResult(String self, List<String> stringList){
        statusCode_success();
        links = new Links();
        links.setSelf(self);
        if (data==null){
            data = Lists.newArrayList();
        }
        data.add(stringList);
    }
}
