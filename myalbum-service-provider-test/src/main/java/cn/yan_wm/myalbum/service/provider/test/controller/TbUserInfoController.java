package cn.yan_wm.myalbum.service.provider.test.controller;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.provider.test.service.TbUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TbUserInfoController extends AbstractBaseController<TbUserInfo> {
    @Autowired
    private TbUserService tbUserService;

    @GetMapping(value = "page/{num}/{size}")
    public PageInfo<TbUserInfo> page(
            @PathVariable int num,
            @PathVariable int size
    ){
        PageInfo<TbUserInfo> page = tbUserService.page(null, num, size);
        return page;
    }

    @GetMapping(value = "hello")
    public String index(){
        return "Hello World !";
    }
}
