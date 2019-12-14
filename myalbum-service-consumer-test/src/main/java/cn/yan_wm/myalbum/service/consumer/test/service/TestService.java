package cn.yan_wm.myalbum.service.consumer.test.service;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.service.consumer.test.config.FeignOauth2RequestInterceptor;
import cn.yan_wm.myalbum.service.consumer.test.service.fallback.TestServiceFallBack;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "myalbum-service-provider-test",fallback = TestServiceFallBack.class,configuration = FeignOauth2RequestInterceptor.class)
public interface TestService {
    @GetMapping("test/page/{num}/{size}")
    public PageInfo<TbUserInfo> page(@PathVariable int num, @PathVariable int size);

    @GetMapping(value = "test/hello")
    public String index();
}
