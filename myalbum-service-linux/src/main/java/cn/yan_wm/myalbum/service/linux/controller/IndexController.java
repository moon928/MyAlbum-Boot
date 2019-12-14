package cn.yan_wm.myalbum.service.linux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
    @RequestMapping(value = "/cpu")
    public String cpu(){
        return "hello";
    }
}
