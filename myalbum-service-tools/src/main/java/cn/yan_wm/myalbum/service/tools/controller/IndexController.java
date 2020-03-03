package cn.yan_wm.myalbum.service.tools.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @program: MyAlbum-Boot
 * @description: test
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/cpu")
    public String cpu(){
        return "index";
    }


    @RequestMapping(value = "/cpuInfo")
    public String cpuInfo(Long id, ModelMap modelMap){
        modelMap.addAttribute("id",id);
        return "cpuInfo";
    }

    @RequestMapping(value = "/cpu1")
    public String cpu1(){
        return "index1";
    }

    @RequestMapping(value = "/cpu2")
    public String cpu2(){
        return "index2";
    }

    @RequestMapping(value = "/cpu3")
    public String cpu3(){
        return "index3";
    }
}
