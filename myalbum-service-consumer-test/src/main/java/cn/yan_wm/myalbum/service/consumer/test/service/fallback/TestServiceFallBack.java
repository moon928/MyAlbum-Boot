package cn.yan_wm.myalbum.service.consumer.test.service.fallback;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.service.consumer.test.service.TestService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

@Component
public class TestServiceFallBack implements TestService {
    @Override
    public PageInfo<TbUserInfo> page(int num, int size) {
        return null;
    }

    @Override
    public String index() {
        return "Provider-Test Down";
    }
}
