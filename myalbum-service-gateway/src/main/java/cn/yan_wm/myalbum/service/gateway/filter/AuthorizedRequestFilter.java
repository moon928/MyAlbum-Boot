package cn.yan_wm.myalbum.service.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @program: MyAlbum-Boot
 * @description: 过滤器
 * @author: yan_zt
 * @create: 2020-05-18 17:37
 */

public class AuthorizedRequestFilter extends ZuulFilter {

    @Override
    public Object run() throws ZuulException {
        return null;
    }

    @Override
    public String filterType() {
        // 在进行Zuul过滤的时候可以设置其过滤执行的位置，那么此时有如下几种类型：
        // 1、pre：在请求发出之前执行过滤，如果要进行访问，肯定在请求前设置头信息
        // 2、route：在进行路由请求的时候被调用；
        // 3、post：在路由之后发送请求信息的时候被调用；
        // 4、error：出现错误之后进行调用
        return "pre";
    }

    /**
     * 设置优先级，数字越大优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 该Filter是否要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


}
