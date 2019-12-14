package cn.yan_wm.myalbum.commons.web;


import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.dto.BaseResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractBaseController<T extends AbstractBaseDomain> {

    private static final String LOGGER_LEVEL_MYDEMO="logging-level";
    @Resource
    protected  HttpServletRequest request;
    @Resource
    protected  HttpServletResponse response;

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    //在每个@RequstMapping 之前执行
    @ModelAttribute
    public void initReqAndRes(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("application/json;charset=UTF-8");
        this.request = request;
        this.response = response;

    }

    protected AbstractBaseResult success(String self, String msg){
        return BaseResultFactory.getInstance(response).build(self,msg);
    }

    protected AbstractBaseResult success(String self, T attribute){
        return BaseResultFactory.getInstance(response).build(self,attribute);
    }

    protected AbstractBaseResult success(String self, List<T> attributes){
        return BaseResultFactory.getInstance(response).build(self,attributes);
    }

    protected AbstractBaseResult success(String self, Long total, int pages,int pageNum,int pageSize, List<T> attributes){
        return BaseResultFactory.getInstance(response).build(self,total,pages,pageNum,pageSize,attributes);
    }

    protected AbstractBaseResult success(String self, int next, int last, List<T>  attributes){
        return BaseResultFactory.getInstance(response).build(self,next,last,attributes);
    }

    protected AbstractBaseResult error(String title,String detail){
        return error(HttpStatus.UNAUTHORIZED.value(),title,detail);
    }

    protected AbstractBaseResult error(int code,String title,String detail){
        return BaseResultFactory.getInstance(response).build(code,title,detail,configurableApplicationContext.getEnvironment().getProperty(LOGGER_LEVEL_MYDEMO));
    }
}
