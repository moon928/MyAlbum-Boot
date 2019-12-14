package cn.yan_wm.myalbum.commons.dto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 通用响应结构工厂
 */
public class BaseResultFactory<T> {

    private static final String LOGGER_LEVEL_DEBUG="DEBUG";

    private static BaseResultFactory baseResultFactory;

    private static HttpServletResponse response;

    private BaseResultFactory(){

    }

    public static BaseResultFactory getInstance(HttpServletResponse response){
        if(baseResultFactory==null){
            synchronized (BaseResultFactory.class){
                if(baseResultFactory==null){
                    baseResultFactory = new BaseResultFactory();
                }
            }
        }
        BaseResultFactory.response = response;
        baseResultFactory.initResponse();
        return baseResultFactory;
    }

    /**
     * String 结果集
     * @param self
     * @param msg
     * @return
     */
    public AbstractBaseResult build(String self,String msg){
        return new SuccessResult(self, msg);
    }


    /**
     * 单笔数据结果集
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self,T attributes){
        return new SuccessResult(self, (AbstractBaseDomain) attributes);
    }

    /**
     * 返回List集合
     * @param self
     * @param attributes
     * @return
     */
    public AbstractBaseResult build(String self,List<T> attributes){
        return new SuccessResult(self,attributes);
    }

    public AbstractBaseResult build(String self, Long total, int pages,int pageNum,int pageSize, List<T> attributes){
        return new SuccessResult(self,total,pages,pageNum,pageSize, attributes);
    }
    /**
     * 多笔数据结果集
     * @param self
     * @param next
     * @param last
     * @return
     */
    public AbstractBaseResult build(String self, int next, int last, List<T> attributes){
        return new SuccessResult(self,next,last, attributes);
    }

    /**
     * 构建请求错误的响应结构
     * @param code
     * @param title
     * @param detail
     * @param level 日志级别只有为debuig时才显示详情
     * @return
     */
    public static AbstractBaseResult build(int code,String title,String detail,String level){
        //设置请求失败的响应码
        response.setStatus(code);
        if (LOGGER_LEVEL_DEBUG.equals(level)){
            return new ErrorResult(code,title,detail);
        }else {
            return new ErrorResult(code,title,null);
        }
    }

    private void initResponse(){
        response.setHeader("Content-Type","application/vnd.api+json");
    }
}
