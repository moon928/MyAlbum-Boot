package cn.yan_wm.myalbum.commons.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 请求成功
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SuccessResult<T extends AbstractBaseDomain> extends AbstractBaseResult{

    private int code;
    private String message;

    private Links links;
    private List<DataBean> data;


    public void statusCode_success(){
        this.code = 200;
        this.message = "success";
    }

    /**
     * 返回String消息
     * @param self
     * @param msg
     */
    public SuccessResult(String self,String msg){
        statusCode_success();
        links = new Links();
        links.setSelf(self);
        DataBean dataBean = new DataBean();
        dataBean.setMsg(msg);
        if (data==null){
            data = Lists.newArrayList();
        }
        data.add(dataBean);
    }

    /**
     * 返回List集合
     * @param self
     * @param attributes
     */
    public SuccessResult(String self, List<T> attributes){
        statusCode_success();
        links = new Links();
        links.setSelf(self);
        attributes.forEach(attribute -> createDataBean(null,attribute));
    }

    /**
     * 单笔数据
     * @param self
     * @param attribute
     */
    public SuccessResult(String self, T attribute){
        statusCode_success();
        links = new Links();
        links.setSelf(self);

        createDataBean(null,attribute);
    }


    /**
     *
     * @param self
     * @param total
     * @param pages
     * @param pageNum
     * @param pageSize
     * @param attributes
     */
    public SuccessResult(String self, Long total, Integer pages,Integer pageNum,Integer pageSize, List<T> attributes){
        statusCode_success();
        links = new Links();
        links.setSelf(self);
        links.setTotal(total);
        links.setPages(pages);
        links.setPageNum(pageNum);
        links.setPageSize(pageSize);

        attributes.forEach(attribute -> createDataBean(self,attribute));
    }

    /**
     * 多笔数据
     * @param self
     * @param next
     * @param last
     * @param attributes
     */
    public SuccessResult(String self, int next, int last,List<T> attributes){
        statusCode_success();
        links = new Links();
        links.setSelf(self);
        links.setNext(self+"?page=" + next);
        links.setLast(self+"?page=" + last);

        attributes.forEach(attribute -> createDataBean(self,attribute));
    }

    public void createDataBean(String self, T attributes){
        if (data==null){
            data = Lists.newArrayList();
        }
        DataBean dataBean = new DataBean();
        if (attributes != null){
            dataBean.setType(attributes.getClass().getSimpleName());
            dataBean.setAttributes(attributes);
        }
        data.add(dataBean);
    }
}
